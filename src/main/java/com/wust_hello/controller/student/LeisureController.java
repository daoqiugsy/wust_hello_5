package com.wust_hello.controller.student;

import com.wust_hello.common.Result;
import com.wust_hello.dto.student.LeisureDto;
import com.wust_hello.service.student.LeisureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/student/time/leisure")
public class LeisureController {

    @Autowired
    private LeisureService leisureService;

    // student leisure time inquire
    @GetMapping
    public Result getLeisure(
            @RequestParam(value = "thisWeek") Boolean thisWeek,
            HttpServletRequest request){
        LeisureDto leisureDto=leisureService.getLeisure(thisWeek,request.getHeader("token"));
        return Result.success(leisureDto);
    }
    //student update leisure time
    @PostMapping
    public Result updateLeisure(
            @RequestParam(value = "thisWeek") Boolean thisWeek,
            @RequestBody LeisureDto leisureDto,
            HttpServletRequest request){
        leisureService.updateLeisure(thisWeek,leisureDto, request.getHeader("token"));
        return Result.success();
    }
}
