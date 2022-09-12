package com.halobios_popularization.Controller;

import com.halobios_popularization.mapper.ReportMapper;
import com.halobios_popularization.model.ReportList;
import com.halobios_popularization.json.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/29 15:22
 */
@Slf4j
@RequestMapping("report")
@RestController
@CrossOrigin
public class ReportController {
    @Autowired
    private ReportMapper reportMapper;

    @GetMapping("/add")
    public JsonData add_sort(Integer comment_id, Integer report_user_id, String report_reason){
//        log.info("operation_id",operation_id);
        log.info("comment_id",comment_id);
        log.info("report_user_id",report_user_id);
        log.info("report_reason",report_reason);

        JsonData jsonData = new JsonData();

        Integer info1=reportMapper.updateCommentState(2,comment_id);
        if(info1!=null){
            if(report_reason==null) report_reason="该用户未提供举报原因";
            Integer info2=reportMapper.addReport(comment_id,report_user_id,report_reason);
            if(info2!=null){
                jsonData.setCode(1);
                jsonData.setData(0);
                jsonData.setMsg("举报成功");
            }else{
                jsonData.setCode(1);
                jsonData.setData(0);
                jsonData.setMsg("举报失败");
            }
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("修改评论表失败");
        }
        return jsonData;

    }

   @GetMapping("/get")
    public List<ReportList> getReportList(){
    List<ReportList>  List=reportMapper.getReportList(0,0);
        return List;
   }

    @GetMapping("/getBycommentId")
    public List<ReportList> getReportListBycommentId(Integer comment_id){
        List<ReportList>  List=reportMapper.getReportListBycommentId(0,0,comment_id);
        return List;
    }
    @GetMapping("/getByCommentState")
    public List<ReportList> getReportListByCommentState(Integer comment_state){
        List<ReportList>  List=reportMapper.getReportListByCommentState(0,0,comment_state);
        return List;
    }
    @GetMapping("/getBySourceType")
    public List<ReportList> getReportListBySourceType(Integer operation_source_type){
        List<ReportList>  List=reportMapper.getReportListBySourceType(0,0,operation_source_type);
        return List;
    }

    @GetMapping("/getBySourceTypeAndCommentState")
    public List<ReportList> getReportListBySourceTypeAndCommentState(Integer operation_source_type,Integer comment_state){
        List<ReportList>  List=reportMapper.getReportListBySourceTypeAndCommentState(0,0,operation_source_type,comment_state);
        return List;
    }

//   提交审核结果
    @ResponseBody
    @PostMapping("/updata")
    public JsonData updata(@RequestParam("comment_state") Integer comment_state,
                           @RequestParam("comment_id") Integer comment_id,
                           @RequestParam("report_result") String report_result) throws JSONException{
        log.info("comment_state:{}", comment_state);
        log.info("comment_id:{}", comment_id);
        JsonData jsonData = new JsonData();
        Integer info=reportMapper.updateComment(comment_state,comment_id);
        if(info!=null){
            Integer info2=reportMapper.updateReport(report_result,comment_id);
            if(info2!=null){
                jsonData.setCode(1);
                jsonData.setData(0);
                jsonData.setMsg("审核提交成功");
            }
            else{
                jsonData.setCode(0);
                jsonData.setData(0);
                jsonData.setMsg("审核提交失败");
            }
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("审核提交失败");
        }
        return jsonData;
    }

}
