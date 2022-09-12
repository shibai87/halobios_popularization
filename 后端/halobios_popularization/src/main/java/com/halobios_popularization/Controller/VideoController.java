package com.halobios_popularization.Controller;

import com.halobios_popularization.mapper.VideoMapper;
import com.halobios_popularization.model.Video;
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
 * @create 2022/3/23 20:46
 */
@Slf4j
@RequestMapping("video")
@RestController
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoMapper videoMapper;

    private String uploadFilePath = "C:/my/code/海洋生物科普Web App/项目/后端/halobios_popularization/src/main/resources/static/video";


    //    获取时间
    @GetMapping("gettime")
    public String gettime() {
        LocalDate now = LocalDate.now();
        return now.toString();
    }
    //    新增活动
    @ResponseBody
    @PostMapping("/newvideo")
    public JsonData updatevideo(@RequestParam("file") MultipartFile file, @RequestParam("video_title") String video_title,
                                @RequestParam("video_info") String video_info,
                                @RequestParam("video_upload") Integer video_upload) throws JSONException {
        JSONObject result = new JSONObject();
        log.info("xx",video_info);
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
        String video_file_name=fileName;
        int addprotect=videoMapper.addvideo(video_title,video_info,video_file_name,video_upload);
        if (addprotect == 0) {
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

    //    查询科普知识列表
    @GetMapping("/get_video_list")
    public List<Video> videosList(Integer video_upload){
        List<Video> a_List=videoMapper.getallvideo(video_upload,0);
        return a_List;
    }
    @GetMapping("get_video")
    public Video videos(Integer video_id){
        Video List=videoMapper.getvideoeById(video_id);
        return List;
    }
    //根据id编辑分类知识
    @ResponseBody
    @PostMapping("/updatavideo")
    public JsonData updatevideo(@RequestParam("file") MultipartFile file,@Param("video_id") Integer video_id,
                                   @RequestParam("video_title") String video_title,
                                   @RequestParam("video_info") String video_info,
                                @RequestParam("old_file_name") String old_file_name,
                                @RequestParam("video_upload") Integer video_upload) throws JSONException {

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
        String video_time=gettime();
        int updateupdatevideo=videoMapper.updatevideo(video_id,video_title,video_info,fileName,video_time,video_upload);
        if (updateupdatevideo == 0) {
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
    //删除
    @GetMapping("delete_video")
    public JsonData deletevideos(Integer video_id,String video_file_name){
        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();
        int List=videoMapper.deletevideoById(video_id,1);
        if(List==1){
            File file= new File(uploadFilePath + "/" + video_file_name);
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

    @GetMapping("search")
    public List<Video> search(){
        List<Video> search=videoMapper.search(1,0);
        return search;
    }

    @GetMapping("get_videoTitle")
    public JsonData get_videoTitle(String video_title){
        JsonData jsonData = new JsonData();
        Video video=videoMapper.getVideoTitle(video_title,1,0);
        if(video!=null){
            Integer value=video.getVideo_id();
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
