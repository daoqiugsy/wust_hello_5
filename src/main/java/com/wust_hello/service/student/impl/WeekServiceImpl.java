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
import com.wust_hello.util.IdGenerator;
import com.wust_hello.util.TokenHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeekServiceImpl extends ServiceImpl<WeekMapper, Week> implements WeekService {

    @Autowired
    private IdGenerator idGenerator;
    @Override
    public TotalWeekDto getTotalReport(LocalDate startTime, LocalDate endTime, Integer page, Integer pageSize,String token)throws BizException {
        if(!endTime.isAfter(startTime)){
            throw new BizException(1001,"开始日期不能超过结束日期");
        }
//        else if(startTime.getDayOfWeek()!= DayOfWeek.SUNDAY){
//            throw new BizException(1004,"开始日期必须为周日");
//        }
//        else if(endTime.getDayOfWeek()!= DayOfWeek.SATURDAY){
//            throw new BizException(1002, "时间段必须为整周");
//        }
        Long userId= TokenHandler.parseToken(token);
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

    @Override
    public void updateReport(Week week, String token) {
        LocalDate startTime=week.getStartTime();
        LocalDate endTime=week.getEndTime();
        if(!endTime.isAfter(startTime)){
            throw new BizException(1001,"开始日期不能超过结束日期");
        }
        else if(startTime.getDayOfWeek()!= DayOfWeek.SUNDAY){
            throw new BizException(1004,"开始日期必须为周日");
        }
        else if(!startTime.plusDays(6L).equals(endTime)){
            throw new BizException(1002, "时间段必须为1周");
        }
        else if(startTime.isAfter(LocalDate.now())){
            throw new BizException(1005,"只能填写或更新本周以及之前周的周报");
        }
        LambdaQueryWrapper<Week> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Week::getStartTime,startTime)
                .eq(Week::getDelete,false);
        Week preWeek=getOne(queryWrapper);
        week.setId(idGenerator.nextId());
        week.setStuId(TokenHandler.parseToken(token));
        save(week);
        if(null!=preWeek){
            preWeek.setDelete(true);
            updateById(preWeek);
        }

    }
}
