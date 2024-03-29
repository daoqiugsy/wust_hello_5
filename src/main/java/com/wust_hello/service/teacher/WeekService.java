package com.wust_hello.service.teacher;

import com.wust_hello.dto.WeekDetailDto;
import com.wust_hello.model.PageBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Component

public interface WeekService {
    PageBean page(Integer page, Integer pageSize, String name, LocalDate start_time, LocalDate end_time,String token);
    //根据ID查询
    WeekDetailDto getById(Integer id,String token);
}
