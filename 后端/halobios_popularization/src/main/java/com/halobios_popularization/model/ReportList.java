package com.halobios_popularization.model;

import lombok.*;

/**
 * @Author 罗
 * @create 2022/4/6 15:26
 */
@Data
public class ReportList {
    //    管理员需要
    private String user_name;
    private  Integer operation_source_type;
    private  Integer operation_source_id;
    private  Integer comment_id;
    private  Integer comment_state;
    private String comment_content;
    private String report_name;
    private String report_reason;
    private String report_result;

}
