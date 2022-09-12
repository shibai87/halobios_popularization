package com.halobios_popularization.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer user_id;
    private String user_tel;
    private String user_name;
    private String user_pwd;
    private Integer user_sex;
    private Integer user_state;
}
