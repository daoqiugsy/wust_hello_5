package com.wust_hello.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wust_hello.model.TotalWeekDto;
import com.wust_hello.model.Week;

import java.time.LocalDate;

public interface WeekService extends IService<Week> {
    public TotalWeekDto getTotalReport(LocalDate startTime, LocalDate endTime, Integer page, Integer pageSize);
}
