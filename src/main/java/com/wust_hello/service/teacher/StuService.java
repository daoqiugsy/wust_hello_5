package com.wust_hello.service.teacher;

import com.wust_hello.common.Result;
import com.wust_hello.model.PageBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface StuService {
    //分页查询学生信息

   PageBean page(Integer page,Integer pageSize,String name,String token);
   //导出学生信息
   public Result download(HttpServletResponse response, String token) throws IOException;

}
