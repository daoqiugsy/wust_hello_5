package com.wust_hello.service.security.impl;

import com.wust_hello.dao.user.UserMapper;
import com.wust_hello.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description: TODO 自己实现一个UserDetailService用来查询数据库中的用户
 *                    并封装成一个UserDetails对象
 * @author Ctungi
 * @date 2024/1/27 20:30
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        User user = userMapper.selectLatestRecord(phone);

        System.out.println(user);
        if(Objects.isNull(user)) {
            throw new RuntimeException("用户或密码错误");
        }

        return new UserDetailsImpl(user);
    }
}