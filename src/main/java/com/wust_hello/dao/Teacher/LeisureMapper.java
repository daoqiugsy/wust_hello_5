package com.wust_hello.mapper;

import org.apache.ibatis.annotations.Param;

public interface LeisureMapper {
    //空闲人数查询（/teacher/getfreenumber）
    int selectLeisure(@Param("time_slot")int time_slot,@Param("date")String date);
}
