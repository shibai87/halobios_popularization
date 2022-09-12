package com.halobios_popularization.model;

import lombok.*;

/**
 * @Author 罗
 * @create 2022/3/28 16:44
 */
@Data
public class Operation {
    private Integer operation_id;
    private  Integer operation_user_id;
    private  Integer operation_source_type;
    private  Integer operation_source_id;
    private  Integer operation_type;
    private  String operation_time;
    private  Integer operation_state;
}
