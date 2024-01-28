package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Long id;
    private String name;
    private int college_id;
    private int major_id;
    private int grade;
    private String edu;
    private Timestamp create_time;
    private Timestamp update_time;
    private boolean delete; //逻辑删除（false未删除，true已删除）


}
