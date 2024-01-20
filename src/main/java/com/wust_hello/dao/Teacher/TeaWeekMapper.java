package com.wust_hello.dao.Teacher;

import com.wust_hello.dto.WeekDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface TeaWeekMapper {
    public List<WeekDetailDto> list(String name, LocalDate start_time, LocalDate end_time);

    public WeekDetailDto getById(Integer id);

}
