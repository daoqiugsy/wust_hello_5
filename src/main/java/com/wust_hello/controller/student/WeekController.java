package com.wust_hello.controller.student;

import com.wust_hello.common.Result;
import com.wust_hello.dto.student.TotalWeekDto;
import com.wust_hello.dto.student.WeekDetailDto;
import com.wust_hello.model.Week;
import com.wust_hello.service.student.WeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Slf4j
@RestController
@Validated
@RequestMapping("/student/time/weekReport")
public class WeekController {
    @Autowired
    private WeekService weekService;

    //student get total week report
    @GetMapping("/total")
    public Result getTotalReport(
            @RequestParam(value = "startTime") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startTime,
            @RequestParam(value = "endTime")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime,
            @RequestParam(value = "page")  Integer page,
            @RequestParam(value = "pageSize")Integer pageSize,
            HttpServletRequest request){
        TotalWeekDto totalWeekDto=weekService.getTotalReport(startTime,endTime,page,pageSize,request.getHeader("token"));
        return Result.success(totalWeekDto);
    }
    //student create or update week report
    @PostMapping
    public Result updateReport(@RequestBody Week week, HttpServletRequest request){
        weekService.updateReport(week,request.getHeader("token"));
        return Result.success();
    }

    //student look up report details
    @GetMapping("/detail")
    public Result getDetals(
            @RequestParam(value = "reportId") Long reportId,
            HttpServletRequest request){
        WeekDetailDto weekDetailDto=weekService.getDetails(reportId,request.getHeader("token"));
        return Result.success(weekDetailDto);
    }
}
