package com.wust_hello.service;

import com.wust_hello.model.PageBean;


public interface StuService {
    //分页查询学生信息

   PageBean page(Integer page,Integer pageSize,String name);
}
