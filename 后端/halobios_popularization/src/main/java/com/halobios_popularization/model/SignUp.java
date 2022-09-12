package com.halobios_popularization.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author 罗
 * @create 2022/4/8 13:52
 */
@Data
public class SignUp {
    private Integer sign_up_id;
    private Integer sign_up_user_id;
    private Integer sign_up_activity_id;
    private Integer sign_up_state;
}
