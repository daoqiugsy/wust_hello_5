package com.wust_hello.service.student;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wust_hello.dto.TotalWeekDto;
import com.wust_hello.dto.WeekDetailDto;
import com.wust_hello.model.Week;

import java.time.LocalDate;

public interface WeekService extends IService<Week> {
    public TotalWeekDto getTotalReport(LocalDate startTime, LocalDate endTime, Integer page, Integer pageSize,String token);
    public WeekDetailDto getDetails(Long reportId,String token);
    public void updateReport(Week week,String token);
}
