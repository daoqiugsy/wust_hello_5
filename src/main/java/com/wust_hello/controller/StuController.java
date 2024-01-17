package com.wust_hello.controller;

import com.wust_hello.model.PageBean;
import com.wust_hello.model.Result;
import com.wust_hello.service.student.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//学生管理
@Slf4j
@RestController

public class StuController {
    //private static Logger log = Logger.getLogger(StuController.class);
    //查询学生信息
    @Autowired
    private StuService stuService;
    @GetMapping("teacher/search_id")
    public Result page(@RequestParam(defaultValue ="1") Integer page,
                       @RequestParam(defaultValue ="10") Integer pageSize,String name) {
        log.info("分页查询，参数：{}，{},{}", page,pageSize,name);
        PageBean pagebean =stuService.page(page, pageSize,name);
        return Result.success(pagebean);
    }


}
