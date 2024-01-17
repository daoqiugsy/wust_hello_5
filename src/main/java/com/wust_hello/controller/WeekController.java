package com.wust_hello.controller;

import com.wust_hello.common.Result;
import com.wust_hello.service.WeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@Validated
@RequestMapping("/student/weekReport")
public class WeekController {
    @Autowired
    private WeekService weekService;

    //student get total week report
    @GetMapping("/total")
    public Result getTotalReport(
            @RequestParam(value = "startTime") LocalDate startTime,
            @RequestParam(value = "endTime") LocalDate endTime,
            @RequestParam(value = "page")  Integer page,
            @RequestParam(value = "pageSize")Integer pageSize){

    }
}
