package com.halobios_popularization.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author 罗
 * @create 2022/3/29 13:38
 */
@Data
public class Report {
    private Integer report_id;
    private Integer comment_id;
    private Integer report_user_id;
    private String report_reason;
    private String report_result;
}
