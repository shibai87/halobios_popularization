package com.halobios_popularization.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author 罗
 * @create 2022/3/21 18:58
 */
@Data
public class Knowledge {
    private Integer knowledge_id;
    private Integer knowledge_sort_id;
    private String  knowledge_title;
    private String knowledge_content;
    private String knowledge_img;
    private String knowledge_time;
    private Integer knowledge_upload;
    private Integer knowledge_state;
}
