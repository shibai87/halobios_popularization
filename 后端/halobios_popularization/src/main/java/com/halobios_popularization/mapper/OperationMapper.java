package com.halobios_popularization.mapper;

import com.halobios_popularization.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/19 19:23
 */
@Repository
@Mapper
public interface OperationMapper {
    //添加操作
    @Insert("INSERT INTO operation(operation_user_id,operation_source_type,operation_source_id,operation_type,operation_time) " +
            "VALUES(#{operation_user_id},#{operation_source_type},#{operation_source_id},#{operation_type},#{operation_time})")
    Integer addOperation(@Param("operation_user_id") Integer operation_user_id,
                         @Param("operation_source_type") Integer operation_source_type,
                         @Param("operation_source_id") Integer operation_source_id,
                         @Param("operation_type") Integer operation_type,
                         @Param("operation_time") String operation_time);

    //    查询操作id
    @Select("SELECT operation_id FROM operation WHERE operation_user_id=#{operation_user_id} " +
            "AND operation_source_type=#{operation_source_type} AND operation_source_id=#{operation_source_id} " +
            "AND operation_type=#{operation_type} AND operation_state=#{operation_state}")
    Integer getOperationId(@Param("operation_user_id") Integer operation_user_id,
                             @Param("operation_source_type") Integer operation_source_type,
                             @Param("operation_source_id") Integer operation_source_id,
                             @Param("operation_type") Integer operation_type,
                             @Param("operation_state") Integer operation_state);

    //    删除操作（评论） 软删
    @Update("UPDATE operation SET operation_state=#{operation_state} WHERE operation_id=#{operation_id}")
    Integer deleteComment(@Param("operation_state") Integer operation_state,@Param("operation_id") Integer operation_id);

//    删除操作 （点赞、收藏）
    @Delete("DELETE FROM operation WHERE operation_id=#{operation_id}")
    Integer deleteOtherOperation(@Param("operation_id") Integer operation_id);

//评论
    //查询每个资源的我的评论
    @Select("SELECT `comment`.* FROM operation,`comment` WHERE operation.operation_id=`comment`.operation_id  " +
            "AND operation.operation_user_id=#{operation_user_id} AND operation.operation_source_type=#{operation_source_type} " +
            "AND operation.operation_source_id=#{operation_source_id} AND operation.operation_type=#{operation_type} " +
            "AND operation.operation_state=#{operation_state}")
    Comment selectCommentById(@Param("operation_user_id") Integer operation_user_id,
                              @Param("operation_source_type") Integer operation_source_type,
                              @Param("operation_source_id") Integer operation_source_id,
                              @Param("operation_type") Integer operation_type,
                              @Param("operation_state") Integer operation_state);
    //   根据添加评论
    @Insert("INSERT INTO comment(operation_id,comment_content) VALUES(#{operation_id},#{comment_content})")
    Integer addComment(@Param("operation_id") Integer operation_id,@Param("comment_content") String comment_content);

    //修改 评论 内容
    @Update("UPDATE `comment` SET comment_content=#{comment_content} WHERE operation_id=#{operation_id}")
    Integer updateCommentContent(@Param("operation_id") Integer operation_id,@Param("comment_content") String comment_content);

    //查询我的评论列表
    @Select("SELECT `comment`.comment_content,`comment`.comment_state,operation.operation_id,operation.operation_source_type,operation.operation_source_id " +
            "FROM operation,`comment` " +
            "WHERE operation.operation_id=`comment`.operation_id AND operation.operation_state=#{operation_state} " +
            "AND operation.operation_user_id=#{operation_user_id}")
    List<CommentList> selectCommentListById(@Param("operation_state") Integer operation_state,
                                            @Param("operation_user_id") Integer operation_user_id);

    //查看 评论列表  用户已登录
    @Select("SELECT `user`.user_name,`comment`.comment_id,`comment`.comment_content ,`comment`.comment_state " +
            "FROM `user`,operation,`comment`  " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id  " +
            "AND `user`.user_state=#{user_state} AND operation.operation_user_id !=#{operation_user_id}  " +
            "AND operation.operation_source_type=#{operation_source_type} AND operation.operation_source_id=#{operation_source_id}  " +
            "AND operation.operation_state=#{operation_state}")
    List<CommentList> selectCommentListHaveid(@Param("user_state") Integer user_state,
                                              @Param("operation_user_id") Integer operation_user_id,
                                              @Param("operation_source_type") Integer operation_source_type,
                                              @Param("operation_source_id") Integer operation_source_id,
                                              @Param("operation_state") Integer operation_state);

    //查看 评论列表  用户未登录
    @Select("SELECT `user`.user_name,`comment`.comment_id,`comment`.comment_content ,`comment`.comment_state " +
            "FROM `user`,operation,`comment`  " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id  " +
            "AND `user`.user_state=#{user_state} AND operation.operation_source_type=#{operation_source_type}  " +
            "AND operation.operation_source_id=#{operation_source_id}  AND operation.operation_state=#{operation_state}")
    List<CommentList> selectCommentList(@Param("user_state") Integer user_state,
                                        @Param("operation_source_type") Integer operation_source_type,
                                        @Param("operation_source_id") Integer operation_source_id,
                                        @Param("operation_state") Integer operation_state);

    //管理员查看 已评论的评论列表
    @Select("SELECT `user`.user_name,`comment`.comment_id,`comment`.comment_content,`comment`.comment_state " +
            "FROM`user`,operation,`comment` " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_source_type=#{operation_source_type} " +
            "AND operation.operation_source_id=#{operation_source_id} AND operation.operation_state=#{operation_state}")
    List<CommentList> selectAllCommentList(@Param("user_state") Integer user_state,
                                          @Param("operation_source_type") Integer operation_source_type,
                                          @Param("operation_source_id") Integer operation_source_id,
                                          @Param("operation_state") Integer operation_state);
    @Select("SELECT `user`.user_name,`comment`.comment_id,`comment`.comment_content,`comment`.comment_state " +
            "FROM`user`,operation,`comment` " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_source_type=#{operation_source_type} " +
            "AND operation.operation_source_id=#{operation_source_id} AND operation.operation_state=#{operation_state} AND `comment`.comment_state=#{comment_state}")
    List<CommentList> selectAllCommentListByCommentState(@Param("user_state") Integer user_state,
                                                           @Param("operation_source_type") Integer operation_source_type,
                                                           @Param("operation_source_id") Integer operation_source_id,
                                                           @Param("operation_state") Integer operation_state,
                                                           @Param("comment_state") Integer comment_state);

    //查询操作人数
    @Select("SELECT COUNT(operation_id) FROM operation " +
            "WHERE operation_source_type=#{operation_source_type} AND operation_source_id=#{operation_source_id}" +
            " AND operation_type=#{operation_type}  AND operation_state=#{operation_state}")
    Integer operationNumber(@Param("operation_source_type") Integer operation_source_type,
                            @Param("operation_source_id") Integer operation_source_id,
                            @Param("operation_type") Integer operation_type,
                            @Param("operation_state") Integer operation_state);



//    查询点赞或评论列表 根据user_id
//        科普知识区
    @Select("SELECT knowledge.knowledge_id,knowledge.knowledge_title " +
            "FROM knowledge," +
            "(SELECT operation_source_id FROM operation " +
            "WHERE operation_user_id=#{operation_user_id} AND operation_source_type=#{operation_source_type} " +
            "AND operation_type=#{operation_type} AND operation_state=#{operation_state}) table1 " +
            "WHERE knowledge.knowledge_id=table1.operation_source_id AND knowledge.knowledge_upload=#{knowledge_upload} " +
            "AND knowledge.knowledge_state=#{knowledge_state}")
    List<Knowledge> selectOperationINKnowledge(@Param("operation_user_id") Integer operation_user_id,
                                               @Param("operation_source_type") Integer operation_source_type,
                                               @Param("operation_type") Integer operation_type,
                                               @Param("operation_state") Integer operation_state,
                                               @Param("knowledge_upload") Integer knowledge_upload,
                                               @Param("knowledge_state") Integer knowledge_state);

    //        活动区
    @Select("SELECT activity.activity_id,activity.activity_title " +
            "FROM activity,(SELECT operation_source_id FROM operation " +
            "WHERE operation_user_id=#{operation_user_id} AND operation_source_type=#{operation_source_type} " +
            "AND operation_type=#{operation_type} AND operation_state=#{operation_state}) table1 " +
            "WHERE activity.activity_id=table1.operation_source_id  AND activity.activity_state=#{activity_state}")
    List<Activity> selectOperationINActivity(@Param("operation_user_id") Integer operation_user_id,
                                             @Param("operation_source_type") Integer operation_source_type,
                                             @Param("operation_type") Integer operation_type,
                                             @Param("operation_state") Integer operation_state,
                                             @Param("activity_state") Integer activity_state);
    //        小课堂区
    @Select("SELECT video.video_id,video.video_title FROM video,(SELECT operation_source_id FROM operation " +
            "WHERE operation_user_id=#{operation_user_id} AND operation_source_type=#{operation_source_type} " +
            "AND operation_type=#{operation_type} AND operation_state=#{operation_state}) table1 " +
            "WHERE video.video_id=table1.operation_source_id  AND video.video_state=#{video_state}")
    List<Video> selectOperationINVideo(@Param("operation_user_id") Integer operation_user_id,
                                       @Param("operation_source_type") Integer operation_source_type,
                                       @Param("operation_type") Integer operation_type,
                                       @Param("operation_state") Integer operation_state,
                                       @Param("video_state") Integer video_state);

}