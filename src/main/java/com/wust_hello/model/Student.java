package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
        private Integer id;
        private String name;
        private Integer collegeId;//学院外键
        private Integer majorId;
        private Integer grade;
        private String edu;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
        private int delete;//是否删除
    }

