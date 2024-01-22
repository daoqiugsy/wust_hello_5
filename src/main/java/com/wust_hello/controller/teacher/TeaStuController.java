package com.wust_hello.controller.teacher;

import com.wust_hello.common.Result;
import com.wust_hello.model.PageBean;
import com.wust_hello.service.teacher.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//学生管理
@Slf4j
@RestController
public class TeaStuController {
    //private static Logger log = Logger.getLogger(StuController.class);
    //查询学生信息
    @Autowired
    private StuService stuService;
    @GetMapping("teacher/search_stu")
    public Result page(@RequestParam(defaultValue ="1") Integer page,
                       @RequestParam(defaultValue ="10") Integer pageSize,
                       String name,
                       HttpServletRequest request) {
        log.info("分页查询，参数：{}，{},{}", page,pageSize,name);
        log.info("token:{}",request.getHeader("token"));
        PageBean pagebean =stuService.page(page, pageSize,name,request.getHeader("token"));
        if (pagebean == null) {
            return Result.error("查询失败");
        }
        return Result.success(pagebean);
    }
    //导出学生信息
    @GetMapping("teacher/info_export")
    public Result export(HttpServletResponse response,HttpServletRequest request) throws IOException {
        log.info("导出学生信息");

        return  stuService.download(response,request.getHeader("token"));
    }


}
