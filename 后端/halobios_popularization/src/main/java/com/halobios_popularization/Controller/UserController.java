package com.halobios_popularization.Controller;



import com.halobios_popularization.mapper.UserMapper;
import com.halobios_popularization.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.halobios_popularization.json.JsonData;

import java.util.List;

@Slf4j
@RequestMapping("user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper userMapper;

//    登录功能
    @GetMapping("login")
    public JsonData login(String user_tel, String user_pwd) {
        log.info("登录");
        log.info("user_tel:{}", user_tel);
        log.info("user_pwd:{}", user_pwd);

        User user = userMapper.userlogin(user_tel, user_pwd);
        JsonData jsonData1 = new JsonData();

//        判断用户是否存在
        if (user == null) {
            jsonData1.setCode(0);
            jsonData1.setData(0);
            jsonData1.setMsg("登录失败");
        }
        else {
          Integer  userId = user.getUser_id();
          Integer  userState=user.getUser_state();
//            判断用户是否是管理员
            if(userId==0){
                jsonData1.setCode(2);
                jsonData1.setData(0);
                jsonData1.setMsg("管理员登录成功");
            }else if (userState==0){
                jsonData1.setCode(1);
                jsonData1.setData(userId);
                jsonData1.setMsg("用户登录成功");
            }else{
                jsonData1.setCode(1);
                jsonData1.setData(userId);
                jsonData1.setMsg("该用户已被管理员注销");
            }
        }
        return jsonData1;
    }

//    注册功能
    @GetMapping("register")
    public JsonData register(String user_name, String user_tel, String user_pwd) {
        log.info("user_name:{}", user_name);
        log.info("user_tel:{}", user_tel);
        log.info("user_pwd:{}", user_pwd);
        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();
        User user = userMapper.judeUserTel(user_tel,0);
        if (user == null) {
            int resultCount = userMapper.saveUser(user_name,user_tel,user_pwd);
            if (resultCount == 0) {
                jsonData1.setCode(1800);
                jsonData1.setData(0);
                jsonData1.setMsg("注册失败");
                return jsonData1;
            } else {
                jsonData2.setCode(1000);
                jsonData2.setData(1);
                jsonData2.setMsg("注册成功");
                return jsonData2;
            }
        } else {
            jsonData1.setCode(1000);
            jsonData1.setData(2);
            jsonData1.setMsg("该号码已注册");
            return jsonData1;
        }
    }

//获取用户信息
    @GetMapping("info")
    public User info(Integer user_id) {
        User info = userMapper.userinfo(user_id);
        return info;
    }

//修改个人信息
@GetMapping("updateuser")
public JsonData updateUser(@Param("user_name") String user_name, @Param("user_pwd") String user_pwd,
                        @Param("user_sex") Integer user_sex, @Param("user_id") Integer user_id){
    JsonData jsonData1 = new JsonData();
    Integer info = userMapper.updateUserInfo(user_name,user_pwd,user_sex,user_id);
    if(info==0){
        jsonData1.setCode(0);
        jsonData1.setData(0);
        jsonData1.setMsg("修改失败");
        return jsonData1;
    }else{
        jsonData1.setCode(1);
        jsonData1.setData(0);
        jsonData1.setMsg("修改成功");
        return jsonData1;
    }
}

    //获取用户信息
    @GetMapping("infoList")
    public List<User> infoList() {
        List<User> info = userMapper.UserList(0,0);
        return info;
    }
    @GetMapping("deleteUser")
    public JsonData deleteUser(Integer user_id){
        log.info("user_id:{}", user_id);
        JsonData jsonData = new JsonData();
        Integer info=userMapper.delecteUser(user_id,1);
        if(info!=null){
            jsonData.setCode(1);
            jsonData.setData(0);
            jsonData.setMsg("删除成功");
        }else{
            jsonData.setCode(0);
            jsonData.setData(0);
            jsonData.setMsg("删除失败");
        }
        return jsonData;
    }
}