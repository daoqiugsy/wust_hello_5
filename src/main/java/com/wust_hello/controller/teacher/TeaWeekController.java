package com.wust_hello.controller.teacher;

import com.wust_hello.common.Result;
import com.wust_hello.dto.WeekDetailDto;
import com.wust_hello.model.PageBean;
import com.wust_hello.service.teacher.WeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/teacher/con_week_rp/")
public class TeaWeekController {
    @Autowired
    private WeekService weekService;
    @GetMapping("search")


    public Result page(@RequestParam(defaultValue ="1") Integer page,
                       @RequestParam(defaultValue ="10") Integer pageSize,
                       String name,
                       @RequestParam(value="start_time",defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate start_time,
                       @RequestParam(value="end_time",defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end_time,
                       HttpServletRequest request) {
        log.info("分页查询，参数：{}，{},{},{},{}", page,pageSize,name,start_time,end_time);
        PageBean pagebean =weekService.page(page, pageSize,name,start_time,end_time,request.getHeader("token"));
        return Result.success(pagebean);
    }
    //根据id查询
    @GetMapping("details/{id}")
    public Result details(@PathVariable("id") Integer id, HttpServletRequest request){
        log.info("根据id查询，参数：{}", id);
        WeekDetailDto weekDetailDto = weekService.getById(id,request.getHeader("token"));
        return Result.success(weekDetailDto);
    }
}
