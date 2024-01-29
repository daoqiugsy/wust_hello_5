package com.wust_hello.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wust_hello.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * @description: TODO 自定义方法查询最新的密码
     * @author Ctungi
     * @date 2024/1/28 12:30
     */
    @Select("SELECT * FROM user where phone = #{phone} ORDER BY create_time DESC LIMIT 1 ;")
    User selectLatestRecord(String phone);

    /**
     * @description: TODO 自定义方法查询同一个手机号最近的三次密码
     * @author Ctungi
     * @date 2024/1/28 12:31
     */
    @Select("SELECT * FROM user where phone = #{phone} ORDER BY create_time DESC LIMIT 3 ;")
    List<User> selectLatestThree(String phone);
}
