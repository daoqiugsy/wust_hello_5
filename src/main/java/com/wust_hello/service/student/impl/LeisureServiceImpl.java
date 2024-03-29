package com.wust_hello.service.student.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wust_hello.dao.student.LeisureMapper;
import com.wust_hello.dto.student.LeisureDto;
import com.wust_hello.dto.student.LeisureModifyDto;
import com.wust_hello.model.Leisure;
import com.wust_hello.service.student.LeisureService;
import com.wust_hello.util.IdGenerator;
import com.wust_hello.util.TokenHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class LeisureServiceImpl extends ServiceImpl<LeisureMapper, Leisure> implements LeisureService {
    private IdGenerator idGenerator=new IdGenerator(1L,1L,1L);

    @Override
    public void updateLeisure(Boolean thisWeek, LeisureModifyDto leisureModifyDto, String token) {
        Long stuId= TokenHandler.parseToken(token);
        LocalDate today=LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame( DayOfWeek.MONDAY));
        LocalDate finalMonday=thisWeek?monday:monday.minusDays(7);
        LambdaUpdateWrapper<Leisure> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Leisure::getStuId,stuId)
                .eq(Leisure::getDeleted,false)
                .eq(Leisure::getDate,finalMonday.plusDays(leisureModifyDto.getDay()-1))
                .eq(Leisure::getTimeSlot,leisureModifyDto.getTime()-1)
                        .set(Leisure::getIsLeisure,1==leisureModifyDto.getStatus());
        update(updateWrapper);
//        short[][] periodList=leisureDto.getLeisureList();
//        List<Leisure> leisureList=new ArrayList<>();
//        for(short i=0;i<9;i++){
//            for(short j=0;j<5;j++){
//                Leisure leisure=new Leisure();
//                leisure.setId(idGenerator.nextId());
//                leisure.setTimeSlot(i);
//                leisure.setIsLeisure(1==periodList[i][j]);
//                leisure.setDate(finalMonday.plusDays(j));
//                leisure.setStuId(stuId);
//                leisureList.add(leisure);
//            }
//        }
//        saveBatch(leisureList);
    }

    @Override
    public LeisureDto getLeisure(Boolean thisWeek, String token) {
        LambdaQueryWrapper<Leisure> queryWrapper=new LambdaQueryWrapper<>();
        Long stuId= TokenHandler.parseToken(token);
        LocalDate today=LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame( DayOfWeek.MONDAY));
        LocalDate finalMonday=thisWeek?monday:monday.minusDays(7);
        queryWrapper.eq(Leisure::getStuId,stuId)
                .eq(Leisure::getDeleted,false)
                .ge(Leisure::getDate,finalMonday)
                .le(Leisure::getDate,finalMonday.plusDays(4));
        List<Leisure> leisureList=list(queryWrapper);
        //initialize the leisure table
        if(leisureList.isEmpty()){
            List<Leisure> initList=new ArrayList<>();
            for(long i=0;i<5;i++){
                for (short j=0;j<9;j++){
                    Leisure leisure=new Leisure();
                    leisure.setId(idGenerator.nextId());
                    leisure.setTimeSlot(j);
                    leisure.setDate(finalMonday.plusDays(i));
                    leisure.setStuId(stuId);
                    boolean isLeisure=j>6?true:false;
                    leisure.setIsLeisure(isLeisure);
                    initList.add(leisure);
                }
            }
            saveBatch(initList);
            leisureList=initList;
        }
        //
        short[][] periodList =new short[9][5];
        leisureList.stream().forEach(item->{
            short dayIndex= Long.valueOf(finalMonday.until(item.getDate(),ChronoUnit.DAYS)).shortValue();
            short periodIndex=item.getTimeSlot();
            periodList[periodIndex][dayIndex]=item.getIsLeisure()?(short)1:(short)0;
        });
        return new LeisureDto(periodList);
    }
}
