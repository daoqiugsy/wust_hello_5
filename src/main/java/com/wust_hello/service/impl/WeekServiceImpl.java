package com.wust_hello.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wust_hello.dao.WeekMapper;
import com.wust_hello.model.TotalWeekDto;
import com.wust_hello.model.Week;
import com.wust_hello.service.WeekService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeekServiceImpl extends ServiceImpl<WeekMapper, Week> implements WeekService {

    @Override
    public TotalWeekDto getTotalReport(LocalDate startTime, LocalDate endTime, Integer page, Integer pageSize) {
        if()
    }
}
