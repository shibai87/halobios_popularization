package com.halobios_popularization.mapper;

import com.halobios_popularization.model.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/23 20:34
 */
@Repository
@Mapper
public interface VideoMapper {
    //    查询所有课堂
    //    新增(上传/保存)课堂
    @Insert("INSERT INTO video(video_title,video_info,video_file_name,video_upload) " +
            "VALUES(#{video_title},#{video_info},#{video_file_name},#{video_upload})")
    int addvideo(@Param("video_title") String video_title,@Param("video_info") String video_info,
                    @Param("video_file_name") String video_file_name,@Param("video_upload") Integer video_upload);
    //    获取课堂列表
    @Select("SELECT * FROM video  WHERE video_upload=#{video_upload} AND video_state=#{video_state}")
    List<Video> getallvideo(@Param("video_upload") Integer video_upload, @Param("video_state") Integer video_state);
    //根据id查询课堂详情
    @Select("SELECT * FROM video WHERE video_id=#{video_id}")
    Video getvideoeById(@Param("video_id") Integer video_id);

    //根据id编辑分类知识
    @Update("UPDATE video SET video_title=#{video_title},video_info=#{video_info},video_file_name=#{video_file_name},video_time=#{video_time},video_upload=#{video_upload} WHERE video_id=#{video_id}")
    int updatevideo(@Param("video_id") Integer video_id,@Param("video_title") String video_title,
                       @Param("video_info") String video_info,@Param("video_file_name") String video_file_name,
                    @Param("video_time") String video_time,@Param("video_upload") Integer video_upload);

    //    根据id删除课堂
    @Update("UPDATE video SET video_state=#{video_state} WHERE video_id=#{video_id}")
    int deletevideoById(@Param("video_id") Integer video_id,@Param("video_state") Integer video_state);

    //搜索
    @Select("SELECT DISTINCT video_id, video_title FROM video " +
            "WHERE video_upload=#{video_upload} AND video_state=#{video_state}")
    List<Video> search(@Param("video_upload") Integer video_upload, @Param("video_state") Integer video_state);

    //查找 标题是否重复
    @Select("SELECT video_id FROM video WHERE video_title=#{video_title} " +
            "AND video_upload=#{video_upload} AND video_state=#{video_state}")
    Video getVideoTitle(@Param("video_title") String video_title, @Param("video_upload") Integer video_upload,
                              @Param("video_state") Integer video_state);
}
