package com.halobios_popularization.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author 罗
 * @create 2022/3/29 20:15
 */
@Data
public class Video {
    private  Integer video_id;
    private String  video_title;
    private String  video_info;
    private String video_file_name;
    private  Integer video_state;
    private  Integer video_upload;
}
