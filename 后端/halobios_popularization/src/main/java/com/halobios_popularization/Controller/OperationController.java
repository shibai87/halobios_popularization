package com.halobios_popularization.Controller;

import com.halobios_popularization.mapper.OperationMapper;
import com.halobios_popularization.model.*;
import com.halobios_popularization.json.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/28 16:42
 */
@Slf4j
@RequestMapping("operation")
@RestController
@CrossOrigin
public class OperationController{
    @Autowired
    private OperationMapper operationMapper;
    private Object List;

    //    获取时间
    @GetMapping("gettime")
    public String gettime() {
        LocalDate now = LocalDate.now();
        return now.toString();
    }

//评论
    //  判断用户是否已评论
    @GetMapping("/judgeOperationId")
    public JsonData judge_operationId(Integer operation_user_id,
                                      Integer operation_source_type,
                                      Integer operation_source_id){
        log.info("operation_user_id:{}",operation_user_id);
        log.info("operation_source_type:{}",operation_source_type);
        log.info("operation_source_id:{}",operation_source_id);
        JsonData jsonData = new JsonData();
        Comment selectCommentById=operationMapper.selectCommentById(operation_user_id,operation_source_type,
                operation_source_id,2,0);
        if(selectCommentById!=null){
            jsonData.setCode(1);
            jsonData.setData(selectCommentById);
            jsonData.setMsg("用户已有过该操作");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("用户未有过该操作");
        }
        return jsonData;

    }
    //添加我的评论
    @ResponseBody
    @PostMapping("/addComment")
    public JsonData add_comment(@Param("operation_user_id") Integer operation_user_id,
                                @Param("operation_source_type") Integer operation_source_type,
                                @Param("operation_source_id") Integer operation_source_id,
                                @Param("comment_content") String comment_content
    ) throws JSONException {
        log.info("operation_user_id:{}",operation_user_id);
        log.info("operation_source_type:{}",operation_source_type);
        log.info("operation_source_id:{}",operation_source_id);
        log.info("comment_content:{}",comment_content);
        JsonData jsonData = new JsonData();
        if(comment_content.matches("(.*)骗子(.*)")){
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("评论内容违禁");
            System.out.print("评论内容违禁");
            return jsonData;
        }
        String operation_time = gettime();
        Integer newOperation = operationMapper.addOperation(operation_user_id, operation_source_type, operation_source_id, 2, operation_time);
        //添加成功后返回1
        if(newOperation!=null){
            Integer operation_id = operationMapper.getOperationId(operation_user_id, operation_source_type, operation_source_id, 2,0);
            //查询到id会返回id
            if(operation_id!=null){
                Integer newSort=operationMapper.addComment(operation_id,comment_content);
                //添加评论成功则返回1
                if(newSort!=null){
                    jsonData.setCode(2);
                    jsonData.setData(newSort);
                    jsonData.setMsg("添加评论成功");
                }else{
                    jsonData.setCode(2);
                    jsonData.setData(newSort);
                    jsonData.setMsg("添加评论失败");
                }

            }else{
                //查询失败
                jsonData.setCode(1);
                jsonData.setData(0);
                jsonData.setMsg("查询操作id失败");
            }
        }else{
            //添加失败
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("添加操作失败");
        }
        return jsonData;
    }

    //修改我的评论内容
    @GetMapping("/updateComment")
    public JsonData update_comment(Integer operation_id,String comment_content){
        log.info("operation_id",operation_id);
        JsonData jsonData = new JsonData();
        Integer info=operationMapper.updateCommentContent(operation_id,comment_content);
        if(info!=0){
            jsonData.setCode(1);
            jsonData.setData(0);
            jsonData.setMsg("修改评论内容成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("修改评论内容失败");
        }
        return jsonData;
    }

    //    查询我的评论列表
    @GetMapping("/getUserCommentList")
    public List<CommentList> get_userCommentList(Integer operation_user_id){
        List<CommentList> user_List=operationMapper.selectCommentListById(0,operation_user_id);
        return user_List;
    }

    //用户查询评论列表
    @GetMapping("/getCommentList")
    public List<CommentList> get_commentList(Integer operation_source_type,
                                             Integer operation_source_id,Integer operation_user_id){
        log.info("operation_user_id:{}",operation_user_id);
        log.info("operation_source_type:{}",operation_source_type);
        log.info("operation_source_id:{}",operation_source_id);
        List<CommentList> List;
        if(operation_user_id!=null){
            List=operationMapper.selectCommentListHaveid(0,operation_user_id,
                                                            operation_source_type,operation_source_id,0);
        }else{
            List=operationMapper.selectCommentList(0, operation_source_type,operation_source_id,0);
        }
        return List;
    }

    //管理员查询评论列表
    @GetMapping("/getAllCommentList")
    public List<CommentList> get_all_commentList(Integer operation_source_type,Integer operation_source_id){
        log.info("operation_source_type:{}",operation_source_type);
        log.info("operation_source_id:{}",operation_source_id);
        List<CommentList> List =operationMapper.selectAllCommentList(0,operation_source_type,operation_source_id,0);
        return List;
    }

    @GetMapping("/getAllCommentListByCommentState")
    public List<CommentList> get_all_commentListByCommentState(Integer operation_source_type,Integer operation_source_id,Integer comment_state){
        log.info("operation_source_type:{}",operation_source_type);
        log.info("operation_source_id:{}",operation_source_id);
        log.info("comment_state:{}",comment_state);
        List<CommentList> List =operationMapper.selectAllCommentListByCommentState(0,operation_source_type,operation_source_id,0,comment_state);
        return List;
    }

//查询操作人数
    @GetMapping("/getOperationNumber")
    public  Integer getOperationNumber(Integer operation_source_type, Integer operation_source_id,
                                       Integer operation_type){
        Integer num=operationMapper.operationNumber(operation_source_type,operation_source_id,operation_type,0);
        return  num;
    }
//    查询用户是否操作
    @GetMapping("getUserOperation")
    public JsonData getUserOperation(Integer user_id, Integer operation_source_type, Integer operation_source_id,
                                    Integer operation_type){
        JsonData jsonData = new JsonData();
        log.info("user_id",user_id);
        Integer operation_id=operationMapper.getOperationId(user_id,operation_source_type,operation_source_id,operation_type,0);
        if(operation_id!=null){
            jsonData.setCode(1);
            jsonData.setData(operation_id);
            jsonData.setMsg("用户已有操作");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("用户未有操作");
        }
        return jsonData;
    }
//添加用户操作
    @GetMapping("addUserOperation")
    public JsonData addUserOperation(Integer user_id, Integer operation_source_type, Integer operation_source_id,
                                     Integer operation_type){
        JsonData jsonData = new JsonData();
        String operation_time = gettime();
        Integer add=operationMapper.addOperation(user_id,operation_source_type,operation_source_id,operation_type,operation_time);
        if(add!=null){
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



    //删除用户操作（评论）
    @GetMapping("deleteComment")
    public JsonData delete_comment(Integer operation_id){
        log.info("operation_id:{}",operation_id);

        JsonData jsonData = new JsonData();
        Integer operation_info=operationMapper.deleteComment(1,operation_id);
        if(operation_info!=0){
            jsonData.setCode(1);
            jsonData.setData(operation_info);
            jsonData.setMsg("删除评论成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(operation_info);
            jsonData.setMsg("删除操作失败");
        }
        return jsonData;
    }
//    删除操作（点赞、收藏）
    @GetMapping("deleteOtherOperation")
    public JsonData delete_other_operation(Integer operation_id){
        JsonData jsonData = new JsonData();
        Integer operation_info=operationMapper.deleteOtherOperation(operation_id);
        if(operation_info!=0){
            jsonData.setCode(1);
            jsonData.setData(0);
            jsonData.setMsg("删除操作成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("删除操作失败");
        }
        return jsonData;
    }


//    查询点赞或评论列表 根据user_id
@GetMapping("/getUserOperationList")
public JsonData get_UserOperationByKnowledge(Integer operation_user_id,Integer operation_source_type,Integer operation_type){
    JsonData jsonData = new JsonData();
    log.info("operation_user_id:{}", operation_user_id);
    log.info("operation_source_type:{}", operation_source_type);
    log.info("operation_type:{}", operation_type);
    if(operation_source_type==0){
        List<Knowledge>  List=operationMapper
                .selectOperationINKnowledge(operation_user_id,operation_source_type,operation_type,
                        0,1,0);
        if(List!=null){
            jsonData.setCode(1);
            jsonData.setData(List);
            jsonData.setMsg("查询成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(List);
            jsonData.setMsg("查询失败");
        }

        return jsonData;
    }else if(operation_source_type==1){
        List<Activity>  List=operationMapper.selectOperationINActivity(operation_user_id,operation_source_type,operation_type,
                0,0);
        if(List!=null){
            jsonData.setCode(1);
            jsonData.setData(List);
            jsonData.setMsg("查询成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(List);
            jsonData.setMsg("查询失败");
        }
    }else{
        List<Video> List=operationMapper.selectOperationINVideo(operation_user_id,operation_source_type,operation_type,
                0,0);
        if(List!=null){
            jsonData.setCode(1);
            jsonData.setData(List);
            jsonData.setMsg("查询成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("查询失败");
        }
    }
    return jsonData;
}

}
