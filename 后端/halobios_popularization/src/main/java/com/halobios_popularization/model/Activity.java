package com.halobios_popularization.model;

import lombok.*;

/**
 * @Author 罗
 * @create 2022/3/23 20:31
 */
@Data
public class Activity {
    private  Integer activity_id;
    private String  activity_title;
    private String  activity_info;
    private String activity_file_name;
    private Integer activity_form;
    private  String activity_place;
    private String activity_begin_time;
    private  String activiy_end_time;
    private  Integer activity_state;
    private  Integer activity_upload;
}
