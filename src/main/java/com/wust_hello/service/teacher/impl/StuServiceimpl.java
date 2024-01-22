package com.wust_hello.service.teacher.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wust_hello.common.Result;
import com.wust_hello.dao.Teacher.StuMapper;
import com.wust_hello.dto.StudentDto;
import com.wust_hello.model.PageBean;
import com.wust_hello.service.teacher.StuService;
import com.wust_hello.util.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Slf4j
@Service
public class StuServiceimpl implements StuService {
    @Autowired
    private StuMapper stuMapper;
    @Override
    public PageBean page(Integer page,Integer pageSize,String name,String token) {
    //校验token
        Long userId= TokenHandler.parseToken(token);
        log.info("userId:{}",userId);
        if(stuMapper.isgetid(userId)==null){
            return null;
        }
       //设置分页参数
        PageHelper.startPage(page,pageSize);
        List<StudentDto>studentList=stuMapper.list(name);
        Page<StudentDto>p=(Page<StudentDto>)studentList;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public Result download(HttpServletResponse response ,String token) throws IOException {
        Long userId= TokenHandler.parseToken(token);
        if(stuMapper.isgetid(userId)==null){
            return Result.error("没有权限");
        }
        List<StudentDto> studentDtolist = stuMapper.findAll();
        log.info("list:{}", studentDtolist);
        response.setContentType("application/vnd.ms-excel");// 设置文本内省
        response.setCharacterEncoding("utf-8");// 设置字符编码
        response.setHeader("Content-disposition", "attachment;filename=学生信息表.xlsx"); // 设置响应头
        EasyExcel.write(response.getOutputStream(), StudentDto.class).sheet("学生信息表").doWrite(studentDtolist); //用io流来写入数据
        return Result.success();
    }
}
