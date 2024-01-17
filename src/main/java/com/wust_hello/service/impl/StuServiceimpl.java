package com.wust_hello.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wust_hello.dao.StuMapper;
import com.wust_hello.model.PageBean;
import com.wust_hello.model.Student;
import com.wust_hello.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StuServiceimpl implements StuService {
    @Autowired
    private StuMapper stuMapper;
    @Override
    public PageBean page(Integer page,Integer pageSize,String name) {
       //设置分页参数
        PageHelper.startPage(page,pageSize);
        List<Student>studentList=stuMapper.list(name);
        Page<Student>p=(Page<Student>)studentList;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
}
