package com.halobios_popularization.mapper;

import com.halobios_popularization.model.ReportList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 罗
 * @create 2022/3/29 15:05
 */
@Repository
@Mapper
public interface ReportMapper {
//用户
    //用户举报 更改comment表
    @Update("UPDATE `comment`  SET comment_state=#{comment_state} WHERE comment_id=#{comment_id}")
    Integer updateCommentState(@Param("comment_state") Integer comment_state,@Param("comment_id") Integer comment_id);
    //用户举报 更改report表
    @Insert("INSERT INTO report(comment_id,report_user_id,report_reason) VALUES(#{comment_id},#{report_user_id},#{report_reason})")
    Integer addReport(@Param("comment_id") Integer comment_id,@Param("report_user_id") Integer report_user_id,
                     @Param("report_reason") String report_reason);
//管理员
//查看审核列表
    @Select("SELECT `user`.user_name,operation.operation_source_type,operation_source_id," +
            "`comment`.comment_id,`comment`.comment_content,`comment`.comment_state," +
            "table1.user_name AS report_name,table1.report_reason,table1.report_result " +
            "FROM `user`,operation,`comment`," +
            "(SELECT `user`.user_name,report.report_user_id,report.comment_id,report.report_reason,report.report_result " +
            "FROM `user`,report WHERE `user`.user_id=report_user_id AND `user`.user_state=0)AS table1 " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `comment`.comment_id=table1.comment_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_state=#{operation_state} ")
    List<ReportList> getReportList(@Param("user_state") Integer user_state, @Param("operation_state") Integer operation_state);

    @Select("SELECT `user`.user_name,operation.operation_source_type,operation_source_id," +
            "`comment`.comment_id,`comment`.comment_content,`comment`.comment_state," +
            "table1.user_name AS report_name,table1.report_reason,table1.report_result " +
            "FROM `user`,operation,`comment`," +
            "(SELECT `user`.user_name,report.report_user_id,report.comment_id,report.report_reason,report.report_result " +
            "FROM `user`,report WHERE `user`.user_id=report_user_id AND `user`.user_state=0)AS table1 " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `comment`.comment_id=table1.comment_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_state=#{operation_state} AND `comment`.comment_id=#{comment_id}")
    List<ReportList> getReportListBycommentId(@Param("user_state") Integer user_state, @Param("operation_state") Integer operation_state,
                                              @Param("comment_id") Integer comment_id);

    @Select("SELECT `user`.user_name,operation.operation_source_type,operation_source_id," +
            "`comment`.comment_id,`comment`.comment_content,`comment`.comment_state," +
            "table1.user_name AS report_name,table1.report_reason,table1.report_result " +
            "FROM `user`,operation,`comment`," +
            "(SELECT `user`.user_name,report.report_user_id,report.comment_id,report.report_reason,report.report_result " +
            "FROM `user`,report WHERE `user`.user_id=report_user_id AND `user`.user_state=0)AS table1 " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `comment`.comment_id=table1.comment_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_state=#{operation_state} AND `comment`.comment_state=#{comment_state}")
    List<ReportList> getReportListByCommentState(@Param("user_state") Integer user_state,
                                                 @Param("operation_state") Integer operation_state,
                                                 @Param("comment_state") Integer comment_state);

    @Select("SELECT `user`.user_name,operation.operation_source_type,operation_source_id," +
            "`comment`.comment_id,`comment`.comment_content,`comment`.comment_state," +
            "table1.user_name AS report_name,table1.report_reason,table1.report_result " +
            "FROM `user`,operation,`comment`," +
            "(SELECT `user`.user_name,report.report_user_id,report.comment_id,report.report_reason,report.report_result " +
            "FROM `user`,report WHERE `user`.user_id=report_user_id AND `user`.user_state=0)AS table1 " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `comment`.comment_id=table1.comment_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_state=#{operation_state} " +
            "AND operation.operation_source_type=#{operation_source_type}")
    List<ReportList> getReportListBySourceType(@Param("user_state") Integer user_state, @Param("operation_state") Integer operation_state,
                                                 @Param("operation_source_type") Integer operation_source_type);

    @Select("SELECT `user`.user_name,operation.operation_source_type,operation_source_id," +
            "`comment`.comment_id,`comment`.comment_content,`comment`.comment_state," +
            "table1.user_name AS report_name,table1.report_reason,table1.report_result " +
            "FROM `user`,operation,`comment`," +
            "(SELECT `user`.user_name,report.report_user_id,report.comment_id,report.report_reason,report.report_result " +
            "FROM `user`,report WHERE `user`.user_id=report_user_id AND `user`.user_state=0)AS table1 " +
            "WHERE `user`.user_id=operation.operation_user_id AND operation.operation_id=`comment`.operation_id " +
            "AND `comment`.comment_id=table1.comment_id " +
            "AND `user`.user_state=#{user_state} AND operation.operation_state=#{operation_state} " +
            "AND operation.operation_source_type=#{operation_source_type}  AND `comment`.comment_state=#{comment_state}")
    List<ReportList> getReportListBySourceTypeAndCommentState(@Param("user_state") Integer user_state,
                                                              @Param("operation_state") Integer operation_state,
                                               @Param("operation_source_type") Integer operation_source_type,
                                                              @Param("comment_state") Integer comment_state);
//    提交审核结果
    @Update("UPDATE `comment` SET comment_state=#{comment_state} WHERE comment_id=#{comment_id}")
    Integer updateComment(@Param("comment_state") Integer comment_state,@Param("comment_id") Integer comment_id);

    @Update("UPDATE report SET report_result=#{report_result} WHERE comment_id=#{comment_id}")
    Integer updateReport(@Param("report_result") String report_result,@Param("comment_id") Integer comment_id);
}
