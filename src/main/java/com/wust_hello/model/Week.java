package com.wust_hello.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Week {
    Long id;
    Long stuId;
    LocalDate startTime;
    LocalDate endTime;
    String currentPlan;
    String nextPlan;
    Boolean isPush;
    String sentiment;
    @TableField(fill= FieldFill.INSERT)
    LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;
    @TableField(fill=FieldFill.INSERT)
    Boolean deleted;
}
