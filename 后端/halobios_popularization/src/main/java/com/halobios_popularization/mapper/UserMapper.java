package com.halobios_popularization.mapper;

import com.halobios_popularization.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
//    登录
    @Select("SELECT user_id,user_state FROM user WHERE user_tel=#{user_tel} and user_pwd=#{user_pwd}")
    User userlogin(@Param("user_tel") String user_tel, @Param("user_pwd") String user_pwd);

//    判断手机号是否已注册
    @Select("SELECT user_tel FROM user WHERE user_tel=#{user_tel} AND user_state=#{user_state}")
    User judeUserTel(@Param("user_tel") String user_tel,@Param("user_state") Integer user_state);

//    新用户注册
@Insert("INSERT into user(user_name,user_tel,user_pwd) VALUES(#{user_name},#{user_tel},#{user_pwd})")
int saveUser(@Param("user_name") String user_name, @Param("user_tel") String user_tel, @Param("user_pwd") String user_pwd);
//获取普通用户所有基本信息
@Select("SELECT * FROM user WHERE user_id=#{user_id}")
    User userinfo(@Param("user_id") Integer user_id);
//
    @Update("UPDATE user SET user_name=#{user_name},user_pwd=#{user_pwd},user_sex=#{user_sex} WHERE user_id=#{user_id}")
    int updateUserInfo(@Param("user_name") String user_name, @Param("user_pwd") String user_pwd,
                       @Param("user_sex") Integer user_sex, @Param("user_id") Integer user_id);
    //获取所有用户所有基本信息
    @Select("SELECT * FROM user WHERE user_id !=#{user_id} AND user_state=#{user_state}")
    List<User> UserList(@Param("user_id") Integer user_id,@Param("user_state") Integer user_state);

//    注销用户
    @Update("UPDATE user SET user_state=#{user_state} WHERE user_id=#{user_id}")
    Integer delecteUser(@Param("user_id") Integer user_id,@Param("user_state") Integer user_state);
}