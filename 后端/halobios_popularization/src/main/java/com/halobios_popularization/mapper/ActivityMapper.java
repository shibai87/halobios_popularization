package com.halobios_popularization.mapper;

import com.halobios_popularization.model.Activity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/23 20:34
 */
@Repository
@Mapper
public interface ActivityMapper {

    //    新增(上传/保存)活动
    @Insert("INSERT INTO activity(activity_title,activity_info,activity_file_name,activity_form,activity_place,activity_begin_time,activiy_end_time,activity_upload) " +
            "VALUES(#{activity_title},#{activity_info},#{activity_file_name},#{activity_form},#{activity_place},#{activity_begin_time},#{activiy_end_time},#{activity_upload})")
    int addactivity(@Param("activity_title") String activity_title, @Param("activity_info") String activity_info,
                    @Param("activity_file_name") String activity_file_name,
                    @Param("activity_form") Integer activity_form, @Param("activity_place") String activity_place,
                    @Param("activity_begin_time") String activity_begin_time, @Param("activiy_end_time") String activiy_end_time,
                    @Param("activity_upload") Integer activity_upload);

    //    获取活动列表
    @Select("SELECT * FROM activity  WHERE activity_upload=#{activity_upload} AND activity_state=#{activity_state}")
    List<Activity> getallactivity(@Param("activity_upload") Integer activity_upload, @Param("activity_state") Integer activity_state);

    //    获取用户活动列表
    @Select("SELECT activity.activity_id,activity.activity_title,activity.activity_file_name FROM activity,sign_up " +
            "WHERE activity.activity_id=sign_up.sign_up_activity_id " +
            "AND activity_upload=#{activity_upload} AND activity.activity_state=#{activity_state} " +
            "AND sign_up.sign_up_user_id=#{sign_up_user_id} AND sign_up.sign_up_state=#{sign_up_state}")
    List<Activity> getallactivityBYUserId(@Param("activity_upload") Integer activity_upload, @Param("activity_state") Integer activity_state,
                                          @Param("sign_up_user_id") Integer sign_up_user_id, @Param("sign_up_state") Integer sign_up_state);

    //根据id查询活动详情
    @Select("SELECT * FROM activity WHERE activity_id=#{activity_id}")
    Activity getactivityeById(@Param("activity_id") Integer activity_id);

    //根据id编辑分类知识
    @Update("UPDATE activity " +
            "SET activity_title=#{activity_title},activity_info=#{activity_info},activity_file_name=#{activity_file_name}," +
            "activity_form=#{activity_form},activity_place=#{activity_place},activity_begin_time=#{activity_begin_time}, " +
            "activiy_end_time=#{activiy_end_time}, activity_upload=#{activity_upload} WHERE activity_id=#{activity_id}")
    Integer updateactivity(@Param("activity_id") Integer activity_id, @Param("activity_title") String activity_title,
                           @Param("activity_info") String activity_info, @Param("activity_file_name") String activity_file_name,
                           @Param("activity_form") Integer activity_form, @Param("activity_place") String activity_place,
                           @Param("activity_begin_time") String activity_begin_time, @Param("activiy_end_time") String activiy_end_time,
                           @Param("activity_upload") Integer activity_upload);

    //    根据id删除活动
    @Update("UPDATE activity SET activity_state=#{activity_state} WHERE activity_id=#{activity_id}")
    int deleteactivityById(@Param("activity_state") Integer activity_state, @Param("activity_id") Integer activity_id);

    //    添加报名
    @Insert("INSERT INTO sign_up(sign_up_user_id,sign_up_activity_id) VALUES(#{sign_up_user_id},#{sign_up_activity_id})")
    Integer add_Sign_up(@Param("sign_up_user_id") Integer sign_up_user_id, @Param("sign_up_activity_id") Integer sign_up_activity_id);

    @Update("UPDATE sign_up SET sign_up_state=#{sign_up_state} WHERE sign_up_id=#{sign_up_id}")
    Integer updata_Sign_up(@Param("sign_up_state") Integer sign_up_state, @Param("sign_up_id") Integer sign_up_id);

    //    查询报名根据id
    @Select("Select sign_up_id From sign_up WHERE sign_up_user_id=#{sign_up_user_id} AND sign_up_activity_id=#{sign_up_activity_id} " +
            "AND sign_up_state=#{sign_up_state}")
    Integer get_Sign_up(@Param("sign_up_user_id") Integer sign_up_user_id, @Param("sign_up_activity_id") Integer sign_up_activity_id,
                        @Param("sign_up_state") Integer sign_up_state);

    //搜索
    @Select("SELECT DISTINCT activity_id, activity_title FROM activity " +
            "WHERE activity_upload=#{activity_upload} AND activity_state=#{activity_state}")
    List<Activity> search(@Param("activity_upload") Integer knowledge_upload,@Param("activity_state") Integer activity_state);

    //查找 标题是否重复
    @Select("SELECT activity_id FROM activity WHERE activity_title=#{activity_title} " +
            "AND activity_upload=#{activity_upload} AND activity_state=#{activity_state}")
    Activity getACtivityTitle(@Param("activity_title") String activity_title,@Param("activity_upload") Integer activity_upload,
                                @Param("activity_state") Integer activity_state);
}
