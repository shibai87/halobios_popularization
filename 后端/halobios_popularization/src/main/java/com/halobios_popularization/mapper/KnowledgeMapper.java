package com.halobios_popularization.mapper;

import com.halobios_popularization.model.Knowledge;
import com.halobios_popularization.model.Sort;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/19 19:23
 */
@Repository
@Mapper
public interface KnowledgeMapper {
//    获取分类表全部数据
    @Select("SELECT * FROM sort")
    List<Sort> getsortList();

    @Select("SELECT * FROM sort WHERE sort_id=#{knowledge_sort_id}")
    List<Sort> getsort(@Param("knowledge_sort_id") Integer knowledge_sort_id);
//    获取科普知识列表
@Select("SELECT * FROM knowledge WHERE knowledge_upload=#{knowledge_upload} AND knowledge_state=#{knowledge_state}")
List<Knowledge> getallknowledge(@Param("knowledge_upload") Integer knowledge_upload,
                                @Param("knowledge_state") Integer knowledge_state);

@Select("SELECT * FROM knowledge WHERE knowledge_upload=#{knowledge_upload} AND knowledge_sort_id=#{knowledge_sort_id} " +
        "AND knowledge_state=#{knowledge_state}")
    List<Knowledge> getknowledge(@Param("knowledge_upload") Integer knowledge_upload,@Param("knowledge_sort_id") Integer knowledge_sort_id,
                                 @Param("knowledge_state") Integer knowledge_state);
//根据id查询知识详情
@Select("SELECT * FROM knowledge WHERE knowledge_id=#{knowledge_id}")
List<Knowledge> getknowledgeById(@Param("knowledge_id") Integer knowledge_id);
    //根据id编辑分类知识
    @Update("UPDATE knowledge SET `knowledge_sort_id`=#{knowledge_sort_id},`knowledge_title`=#{knowledge_title}," +
            "`knowledge_content`=#{knowledge_content},`knowledge_img`=#{knowledge_img},`knowledge_time`=#{knowledge_time} " +
            "WHERE `knowledge_id`=#{knowledge_id}")
    int updateSort(@Param("knowledge_id") Integer knowledge_id,@Param("knowledge_sort_id") Integer knowledge_sort_id,
                   @Param("knowledge_title") String knowledge_title,
                   @Param("knowledge_content") String knowledge_content,@Param("knowledge_img") String knowledge_img,
                   @Param("knowledge_time") String knowledge_time);

//    根据id删除知识
    @Update("UPDATE knowledge SET knowledge_state=#{knowledge_state} WHERE knowledge_id=#{knowledge_id}")
    int deleteknowledgeById(@Param("knowledge_state") Integer knowledge_state,@Param("knowledge_id") Integer knowledge_id);
//    新增(上传)分类知识
@Insert("INSERT INTO knowledge(knowledge_sort_id,knowledge_title,knowledge_content,knowledge_img,knowledge_time,knowledge_upload) " +
        "VALUES(#{knowledge_sort_id},#{knowledge_title},#{knowledge_content},#{knowledge_img},#{knowledge_time},#{knowledge_upload})")
     int addsort(@Param("knowledge_sort_id") Integer knowledge_sort_id,@Param("knowledge_title") String knowledge_title,
                 @Param("knowledge_content") String knowledge_content,@Param("knowledge_img") String knowledge_img,
                 @Param("knowledge_time") String knowledge_time,@Param("knowledge_upload") Integer knowledge_upload);
//搜索
@Select("SELECT DISTINCT knowledge_id, knowledge_title FROM knowledge " +
        "WHERE knowledge_upload=#{knowledge_upload} AND knowledge_state=#{knowledge_state}")
    List<Knowledge> search(@Param("knowledge_upload") Integer knowledge_upload,@Param("knowledge_state") Integer knowledge_state);

//查找 标题是否重复
    @Select("SELECT knowledge_id FROM knowledge WHERE knowledge_title=#{knowledge_title} " +
            "AND knowledge_upload=#{knowledge_upload} AND knowledge_state=#{knowledge_state}")
    Knowledge getKnowledgeTitle(@Param("knowledge_title") String knowledge_title,@Param("knowledge_upload") Integer knowledge_upload,
                            @Param("knowledge_state") Integer knowledge_state);
}
