package com.halobios_popularization.Controller;

import com.halobios_popularization.mapper.ActivityMapper;

import com.halobios_popularization.model.Activity;
import com.halobios_popularization.json.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


/**
 * @Author 罗
 * @create 2022/3/23 20:46
 */
@Slf4j
@RequestMapping("activity")
@RestController
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityMapper activityMapper;

    private String uploadFilePath = "C:/my/code/海洋生物科普Web App/项目/后端/halobios_popularization/src/main/resources/static/activity";
//                                       C:\my\code\海洋生物科普Web App\项目\后端\halobios_popularization\src\main\resources\static\activity
    //    新增活动
    @ResponseBody
    @PostMapping("/newactivity")
    public JsonData newactivity(@RequestParam("file") MultipartFile file,
                                @RequestParam("activity_title") String activity_title,
                                @RequestParam("activity_info") String activity_info,
                                @RequestParam("activity_form") Integer activity_form,
                                @RequestParam("activity_place") String activity_place,
                                @RequestParam("activity_begin_time") String activity_begin_time,
                                @RequestParam("activiy_end_time") String activiy_end_time,
                                @RequestParam("activity_upload") Integer activity_upload) throws JSONException {
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
        String activity_file_name=fileName;
        int addprotect=activityMapper.addactivity(activity_title,activity_info,activity_file_name,activity_form,activity_place,activity_begin_time,activiy_end_time,activity_upload);
        if (addprotect == 0) {
            jsonData1.setCode(0);
            jsonData1.setData(0);
            jsonData1.setMsg("添加失败");
            return jsonData1;
        } else {
            jsonData2.setCode(0);
            jsonData2.setData(1);
            jsonData2.setMsg("添加成功");
            return jsonData2;
        }

    }

    //    查询科普知识列表
    @GetMapping("/get_activity_list")
    public List<Activity> activitysList(Integer activity_upload){
//        log.info("activity_upload:{}",activity_upload);
        List<Activity> a_List=activityMapper.getallactivity(activity_upload,0);
            return a_List;
        }

    //    查询科普知识列表
    @GetMapping("/get_activity_listByUserId")
    public List<Activity> activitysListByUserId(Integer sign_up_user_id){
//        log.info("activity_upload:{}",activity_upload);
        List<Activity> a_List=activityMapper.getallactivityBYUserId(1,0, sign_up_user_id,0);
        return a_List;
    }

    @GetMapping("get_activity")
    public Activity activitys(Integer activity_id){
        Activity List=activityMapper.getactivityeById(activity_id);
        return List;
    }
    //根据id编辑分类知识
    @ResponseBody
    @PostMapping("/updataactivity")
    public JsonData updateactivity(@RequestParam("file") MultipartFile file,
                                   @RequestParam("activity_id") Integer activity_id,
                                   @RequestParam("activity_title") String activity_title,
                                   @RequestParam("activity_info") String activity_info,
                                   @RequestParam("activity_form") Integer activity_form,
                                   @RequestParam("activity_place") String activity_place,
                                   @RequestParam("activity_begin_time") String activity_begin_time,
                                   @RequestParam("activiy_end_time") String activiy_end_time,
                                   @RequestParam("old_file_name") String old_file_name,
                                   @RequestParam("activity_upload") Integer activity_upload) throws JSONException {
        File file1= new File(uploadFilePath + "/" + old_file_name);
//        if (file1.exists()) {
//            file1.delete();
//            System.out.println("===========删除成功=================");
//        } else {
//            System.out.println("===============删除失败==============");
//        }

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
        String activity_file_name=fileName;
        Integer addprotect=activityMapper.updateactivity(activity_id,activity_title,activity_info,activity_file_name,
                activity_form,activity_place,activity_begin_time,activiy_end_time,activity_upload);
        if (addprotect == 0) {
            jsonData1.setCode(1800);
            jsonData1.setData(0);
            jsonData1.setMsg("修改失败");
            return jsonData1;
        } else {
            jsonData2.setCode(1000);
            jsonData2.setData(1);
            jsonData2.setMsg("修改成功");
            return jsonData2;
        }

    }
    //删除
    @GetMapping("delete_activity")
    public JsonData deleteactivitys(Integer activity_id,String activity_file_name){
        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();
        int List=activityMapper.deleteactivityById(1,activity_id);
        if(List==1){
            File file= new File(uploadFilePath + "/" + activity_file_name);
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
//添加报名
    @GetMapping("add_SignUp")
    public JsonData add_SignUp(Integer sign_up_user_id,Integer sign_up_activity_id){
        JsonData jsonData = new JsonData();
        Integer sign_up=activityMapper.add_Sign_up(sign_up_user_id,sign_up_activity_id);
        if(sign_up!=null){
            jsonData.setCode(1);
            jsonData.setData(0);
            jsonData.setMsg("添加成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("添加失败");
        }
        return jsonData;
    }
//查询报名
@GetMapping("get_SignUp")
public JsonData get_SignUp(Integer sign_up_user_id,Integer sign_up_activity_id){
    JsonData jsonData = new JsonData();
    Integer info=activityMapper.get_Sign_up(sign_up_user_id,sign_up_activity_id,0);
    if(info!=null){
        jsonData.setCode(1);
        jsonData.setData(info);
        jsonData.setMsg("查询成功");
    }else{
        jsonData.setCode(0);
        jsonData.setData(info);
        jsonData.setMsg("查询失败");
    }
    return jsonData;
}

//修改报名
    @GetMapping("updata_SignUp")
    public JsonData updata_SignUp(Integer sign_up_id){
        JsonData jsonData = new JsonData();
        Integer i=activityMapper.updata_Sign_up(1,sign_up_id);
        if(i!=null){
            jsonData.setCode(1);
            jsonData.setData(0);
            jsonData.setMsg("修改成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("修改失败");
        }
        return jsonData;
    }

    @GetMapping("search")
    public List<Activity> search(){
        List<Activity> search=activityMapper.search(1,0);
        return search;
    }

    @GetMapping("get_activityTitle")
    public JsonData get_activityTitle(String activity_title){
        JsonData jsonData = new JsonData();
        Activity activity=activityMapper.getACtivityTitle(activity_title,1,0);
        if(activity!=null){
            Integer value=activity.getActivity_id();
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
