package com.wust_hello.service.student;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wust_hello.dto.student.LeisureDto;
import com.wust_hello.model.Leisure;

public interface LeisureService extends IService<Leisure> {
    void updateLeisure(Boolean thisWeek,LeisureDto leisureDto, String token) ;

    public LeisureDto getLeisure(Boolean thisWeek, String token);
}
