package com.wust_hello.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    /*打卡记录id*/
    private Long id;
    /*打卡学生id*/
    private Long stuId;
    /*打卡开始时间*/
    String startTime;
    String endTime;
    /*打卡日期*/
    private String date;
    /*有效时间*/
    private Integer valid;
    /*创建时间*/
    @TableField(fill= FieldFill.INSERT)
    LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;
    @TableField(fill=FieldFill.INSERT)
    Boolean deleted;
}
