package com.wust_hello.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Leisure {
    Long id;
    Short period;
    Boolean isLeisure;
    LocalDate date;
    Long stuId;
    @TableField(fill= FieldFill.INSERT)
    LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;
    @TableField(fill=FieldFill.INSERT)
    Boolean delete;

}
