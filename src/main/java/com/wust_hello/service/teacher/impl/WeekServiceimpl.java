package com.wust_hello.service.teacher.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wust_hello.dao.Teacher.TeaWeekMapper;
import com.wust_hello.dto.WeekDetailDto;
import com.wust_hello.model.PageBean;
import com.wust_hello.service.teacher.WeekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@Slf4j
public class WeekServiceimpl implements WeekService {
    //分页查询周报信息
    @Autowired
    private TeaWeekMapper teaWeekMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, LocalDate start_time,LocalDate end_time){
        //设置分页参数

        PageHelper.startPage(page,pageSize);
        List<WeekDetailDto> weekDetailDtosList= teaWeekMapper.list(name,start_time,end_time);
        log.info("查询到的数据为："+weekDetailDtosList);
        Page<WeekDetailDto> p=(Page<WeekDetailDto>)weekDetailDtosList;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
    @Override
    public WeekDetailDto getById(Integer id){
        return teaWeekMapper.getById(id);
    }
}
