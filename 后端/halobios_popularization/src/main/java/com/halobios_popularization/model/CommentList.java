package com.halobios_popularization.model;

import lombok.*;

/**
 * @Author 罗
 * @create 2022/3/28 16:44
 */
@Data
public class CommentList {
//    评论列表
    private String user_name;
    private Integer comment_id;
    private String comment_content;
    private Integer user_state;
    //查看某用户评论列表需要
    private  Integer operation_source_type;
    private  Integer operation_source_id;
    private Integer comment_state;

    private Integer user_id;
    private String user_tel;
    private String user_pwd;
    private Integer user_sex;

    private Integer operation_id;
    private  Integer operation_user_id;

    private  Integer operation_type;
    private  String operation_time;
    private  Integer operation_state;



}
