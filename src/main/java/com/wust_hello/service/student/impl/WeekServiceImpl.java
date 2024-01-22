package com.wust_hello.service.student.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wust_hello.common.exception.BizException;
import com.wust_hello.dao.student.WeekMapper;
import com.wust_hello.dto.TotalWeekDto;
import com.wust_hello.dto.WeekDetailDto;
import com.wust_hello.model.Week;
import com.wust_hello.dto.WeekSummary;
import com.wust_hello.service.student.WeekService;
import com.wust_hello.util.IdGenerator;
import com.wust_hello.util.TokenHandler;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class WeekServiceImpl extends ServiceImpl<WeekMapper, Week> implements WeekService {

    private IdGenerator idGenerator=new IdGenerator(1L,1L,1L);
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
                .le(Week::getEndTime,endTime)
                .eq(Week::getDeleted,false);
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
    public WeekDetailDto getDetails(Long reportId, String token) {
        LambdaQueryWrapper<Week> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Week::getStuId,TokenHandler.parseToken(token))
                .eq(Week::getId,reportId)
                .eq(Week::getDeleted,false);
        Week week=getOne(queryWrapper);
        if(null==week){
            throw new BizException(1003,"查不到该条周报或权限不足");
        }
        WeekDetailDto weekDetailDto=new WeekDetailDto();
        BeanUtils.copyProperties(week,weekDetailDto);
        return weekDetailDto;
    }

    @Override
    public void updateReport(Week week, String token) {
        LocalDate startTime=week.getStartTime();
        LocalDate endTime=week.getEndTime();
        if(null==startTime||null==endTime){
            throw new BizException(1007,"未选择时间");
        }
        else if(week.getCurrentPlan().equals("")||week.getNextPlan().equals("")||week.getSentiment().equals("")){
            throw new BizException(1008,"输入不全");
        }
        else if(!endTime.isAfter(startTime)){
            throw new BizException(1001,"开始日期不能超过结束日期");
        }
        else if(startTime.getDayOfWeek()!= DayOfWeek.MONDAY){
            throw new BizException(1004,"开始日期必须为周一");
        }
        else if(!startTime.plusDays(6L).equals(endTime)){
            throw new BizException(1002, "时间段必须为1周");
        }
        else if(startTime.isAfter(LocalDate.now())){
            throw new BizException(1005,"只能填写或更新本周以及之前周的周报");
        }
        LambdaQueryWrapper<Week> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Week::getStartTime,startTime)
                .eq(Week::getDeleted,false);
        Week preWeek=getOne(queryWrapper);
        week.setId(idGenerator.nextId());
        week.setStuId(TokenHandler.parseToken(token));
        save(week);
        if(null!=preWeek){
            preWeek.setDeleted(true);
            updateById(preWeek);
        }

    }
}
