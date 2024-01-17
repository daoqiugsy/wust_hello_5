package com.wust_hello.service.student.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wust_hello.common.exception.BizException;
import com.wust_hello.dao.WeekMapper;
import com.wust_hello.dto.TotalWeekDto;
import com.wust_hello.dto.WeekDetailDto;
import com.wust_hello.model.Week;
import com.wust_hello.dto.WeekSummary;
import com.wust_hello.service.student.WeekService;
import com.wust_hello.util.TokenHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeekServiceImpl extends ServiceImpl<WeekMapper, Week> implements WeekService {

    @Override
    public TotalWeekDto getTotalReport(LocalDate startTime, LocalDate endTime, Integer page, Integer pageSize,String token)throws BizException {
        if(!endTime.isAfter(startTime)){
            throw new BizException(1001,"开始日期不能超过结束日期");
        }
        else{
            long days = startTime.until(endTime.plusDays(1), ChronoUnit.DAYS);
            if(0!=days%7L) {
                throw new BizException(1002, "时间段必须为整周");
            }
        }
        Integer userId= TokenHandler.parseToken(token);
        LambdaQueryWrapper<Week> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Week::getStuId,userId)
                .ge(Week::getStartTime,startTime)
                .le(Week::getEndTime,endTime);
        Page<Week> pageInfo=new Page<>(page,pageSize);
        page(pageInfo,queryWrapper);
        List<WeekSummary> summaryList=pageInfo
                .getRecords()
                .stream()
                .map(item->{
                    WeekSummary weekSummary=new WeekSummary();
                    BeanUtils.copyProperties(item,weekSummary);
                    return weekSummary;
                }).collect(Collectors.toList());
        TotalWeekDto totalWeekDto=new TotalWeekDto();
        totalWeekDto.setTotalPage(pageInfo.getTotal());
        totalWeekDto.setSummaryList(summaryList);
        return totalWeekDto;
    }

    @Override
    public WeekDetailDto getDetails(Integer reportId, String token) {
        LambdaQueryWrapper<Week> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Week::getStuId,TokenHandler.parseToken(token))
                .eq(Week::getId,reportId);
        Week week=getOne(queryWrapper);
        if(null==week){
            throw new BizException(1003,"查不到该条周报");
        }
        WeekDetailDto weekDetailDto=new WeekDetailDto();
        BeanUtils.copyProperties(week,weekDetailDto);
        return weekDetailDto;
    }
}
