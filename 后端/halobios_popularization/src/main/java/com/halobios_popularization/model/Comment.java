package com.halobios_popularization.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author 罗
 * @create 2022/3/28 15:36
 */
@Data
public class Comment {
        private Integer comment_id;
        private Integer operation_id;
        private String comment_content;
        private Integer comment_state;
}
