package com.halobios_popularization.Controller;

import com.halobios_popularization.mapper.KnowledgeMapper;
import com.halobios_popularization.model.  Knowledge;
import com.halobios_popularization.model.Sort;
import com.halobios_popularization.json.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/19 20:12
 */
@Slf4j
@RequestMapping("knowledge")
@RestController
@CrossOrigin
public class KnowledgeController {
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    //    获取时间
    @GetMapping("gettime")
    public String gettime() {
        LocalDate now = LocalDate.now();
        return now.toString();
    }

    private String uploadFilePath = "C:/my/code/海洋生物科普Web App/项目/后端/halobios_popularization/src/main/resources/static/knowledge";
//分类                                C:\my\code\海洋生物科普Web App\项目\后端\halobios_popularization\src\main\resources\static\activity
    //    获取所有分类列表
    @GetMapping("get_all_sort")
    public List<Sort> sortList(){
        List<Sort> all_sort=knowledgeMapper.getsortList();
        return all_sort;
    }

    // 根据id获取分类详细
    @GetMapping("get_sort")
    public List<Sort> sort(Integer knowledge_sort_id){
        List<Sort> all_sort=knowledgeMapper.getsort(knowledge_sort_id);
        return all_sort;
    }
//知识
    //查询科普知识列表
    @GetMapping("get_knowledge_list")
        public JsonData knowledgesList(Integer knowledge_upload, Integer options_id){
        JsonData jsonData = new JsonData();
        log.info("knowledge_upload:{}",knowledge_upload);
//        log.info("options_id:{}",options_id);
        //      knowledge_upload=1 查询已上传的知识 knowledge_upload=0 查询未上传的知识
    //   查询某分类下的列表
        if(options_id!=null){
            List<Knowledge> List=knowledgeMapper.getknowledge(knowledge_upload,options_id,0);
//            情况1
          if(List.isEmpty()){
                jsonData.setCode(1);
                jsonData.setData(List);
                jsonData.setMsg("分类查询列表为空");
            }
//          情况2
            else{
                jsonData.setCode(1);
                jsonData.setData(List);
                jsonData.setMsg("分类列表查询成功");
            }
                 return jsonData;
    //        查询所有列表
        }else{
//            情况3
            List<Knowledge> List=knowledgeMapper.getallknowledge(knowledge_upload,0);
            if(List.isEmpty()){
                jsonData.setCode(0);
                jsonData.setData(List);
                jsonData.setMsg("查询列表为空");
            }
//            情况4
            else{
                jsonData.setCode(0);
                jsonData.setData(List);
                jsonData.setMsg("列表查询成功");
            }
                 return jsonData;
        }
            }

    //根据id查询知识
    @GetMapping("get_knowledge")
    public List<Knowledge> knowledges(Integer knowledge_id){
        List<Knowledge> List=knowledgeMapper.getknowledgeById(knowledge_id);
            return List;
    }

    //根据id编辑分类知识
    @ResponseBody
    @PostMapping("/updatasort")
    public JsonData updatesort(@RequestParam("file") MultipartFile file,@Param("knowledge_id") Integer knowledge_id,
                               @RequestParam("knowledge_sort_id") Integer knowledge_sort_id,@RequestParam("knowledge_title") String knowledge_title,
                               @RequestParam("knowledge_content") String knowledge_content,
                               @RequestParam("old_img_name") String old_img_name) throws JSONException {
        log.error("knowledge_sort_id:{}",knowledge_sort_id);
        File file1= new File(uploadFilePath + "/" + old_img_name);
//        if (file1.exists()) {
//            file1.delete();
//            System.out.println("===========替换成功=================");
//        } else {
//            System.out.println("===============替换失败==============");
//        }


        JSONObject result = new JSONObject();
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        log.info("上传文件名称为:{},后缀名为:{}!", fileName, suffixName);
        File fileTempObj = new File(uploadFilePath + "/" + fileName);
        if (!fileTempObj.getParentFile().exists()) {
            fileTempObj.getParentFile().mkdirs();
        }
        if (fileTempObj.exists()) {
            result.put("error", "文件已存在");
        }
        try {
            file.transferTo(fileTempObj);
        } catch (Exception e) {
            log.error("发生错误:{}", e);
            result.put("error", e.getMessage());
        }

        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();
        String knowledge_time=gettime();
        int updatesort=knowledgeMapper.updateSort(knowledge_id,knowledge_sort_id,knowledge_title,knowledge_content,fileName,knowledge_time);
        if (updatesort == 0) {
            jsonData1.setCode(1800);
            jsonData1.setData(0);
            jsonData1.setMsg("添加失败");
            return jsonData1;
        } else {
            jsonData2.setCode(1000);
            jsonData2.setData(1);
            jsonData2.setMsg("添加成功");
            return jsonData2;
        }
    }
    //根据id删除分类知识
    @GetMapping("delete_knowledge")
    public JsonData deleteknowledges(Integer knowledge_id,String knowledge_img){
        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();
        int List=knowledgeMapper.deleteknowledgeById(1,knowledge_id);
        if(List==1){
            File file= new File(uploadFilePath + "/" + knowledge_img);
            if (file.exists()) {
//                file.delete();
                System.out.println("===========删除成功=================");
                jsonData1.setData(1);
                jsonData1.setMsg("删除成功");
                return jsonData1;
            } else {
                System.out.println("===============删除失败==============");
                jsonData1.setData(1);
                jsonData1.setMsg("删除图片失败");
                return jsonData1;
            }
        }else{
            jsonData2.setData(0);
            jsonData2.setMsg("删除失败");
            return jsonData2;
        }

    }
//    新增分类知识
@ResponseBody
@PostMapping("/newsort")
    public JsonData newsort(@RequestParam("file") MultipartFile file,
                            @RequestParam("knowledge_sort_id") Integer knowledge_sort_id,
                            @RequestParam("knowledge_title") String knowledge_title,
                            @RequestParam("knowledge_content") String knowledge_content,
                            @RequestParam("knowledge_upload") Integer knowledge_upload) throws JSONException {
    log.info("knowledge_sort_id:{}", knowledge_sort_id);
    log.info("knowledge_title:{}",knowledge_title);
    log.info("knowledge_content:{}",knowledge_content);
    log.info("knowledge_upload:{}",knowledge_upload);
        JSONObject result = new JSONObject();

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传文件名称为:{},后缀名为:{}!", fileName, suffixName);
        File fileTempObj = new File(uploadFilePath + "/" + fileName);
        if (!fileTempObj.getParentFile().exists()) {
            fileTempObj.getParentFile().mkdirs();
        }
        if (fileTempObj.exists()) {
            result.put("error", "文件已存在");
        }
        try {
            file.transferTo(fileTempObj);
        } catch (Exception e) {
            log.error("发生错误:{}", e);
            result.put("error", e.getMessage());
        }

        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();
        String knowledge_time=gettime();
//        log.info("knowledge_time:{}", knowledge_time);
        int addsort=knowledgeMapper.addsort(knowledge_sort_id,knowledge_title,knowledge_content,fileName,knowledge_time,knowledge_upload);
        log.error("addsort:{}", addsort);
        if (addsort == 0) {
            jsonData1.setCode(1800);
            jsonData1.setData(0);
            jsonData1.setMsg("添加失败");
            return jsonData1;
        } else {
            jsonData2.setCode(1000);
            jsonData2.setData(1);
            jsonData2.setMsg("添加成功");
            return jsonData2;
        }

    }

    @GetMapping("search")
    public List<Knowledge> search(){
        List<Knowledge> search=knowledgeMapper.search(1,0);
        return search;
    }

    @GetMapping("get_knowledgeTitle")
    public JsonData get_knowledgeTitle(String knowledge_title){
        JsonData jsonData = new JsonData();
        Knowledge knowledge=knowledgeMapper.getKnowledgeTitle(knowledge_title,1,0);
        if(knowledge!=null){
            Integer value=knowledge.getKnowledge_id();
            if (value!=null) {
                jsonData.setCode(0);
                jsonData.setData(value);
                jsonData.setMsg("查询成功");

            } else {
                jsonData.setCode(1);
                jsonData.setData(0);
                jsonData.setMsg("查询失败");

            }
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("查询失败");
        }

        return jsonData;
    }
}