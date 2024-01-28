package com.wust_hello.mapper;

import org.apache.ibatis.annotations.Param;
import com.wust_hello.utilpojo.CardMsg;

import java.util.List;

public interface CardMapper {
    /**
     * Mybatis获取参数值的两种方式：${}和#{}
     *${}的本质就是字符串拼接，#{}的本质就是占位符赋值
     * ${}使用字符串拼接的方式拼接sql,若为字符串类型或日期类型的字段进行赋值时，需要手动加单引号
     * 但是#{}使用占位符赋值的方式拼接sql,此时字符串或日期类型的字段进行赋值时，可以自动添加单引号
     */

    //所有学生打卡信息显示（/teacher/getall）
    //默认从今向前
    List<CardMsg> findAllCardMsg(@Param("pageSize") int pageSize,@Param("pageNum")int pageNum,@Param("startData")int startData,@Param("endData")int endData);

    //学生打卡信息查询（/teacher/getmessage）
    List<CardMsg> findCardMsg(@Param("pageSize") int pageSize,@Param("name") String name,@Param("pageNum")int pageNum,@Param("startData")int startData,@Param("endData")int endData);

    //按照打卡日期正序排列（/teacher/sortdate）（从前至今）
    List<CardMsg> sortDateCardMsg(@Param("pageSize")int pageSize,@Param("pageNum")int pageNum,@Param("startData")int startData,@Param("endData")int endData);
}