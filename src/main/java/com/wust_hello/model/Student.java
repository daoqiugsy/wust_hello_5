package com.wust_hello.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
        @TableId(value = "id", type = IdType.INPUT)
        private Long id;
        private String name;
        private Long collegeId;//学院外键
        private Long majorId;
        private Integer grade;
        private String edu;
        @TableField(fill = FieldFill.INSERT)
        private LocalDateTime createTime;
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private LocalDateTime updateTime;
        private boolean deleted;//是否删除
    }

