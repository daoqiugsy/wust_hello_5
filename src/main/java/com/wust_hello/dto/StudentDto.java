package com.wust_hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private Integer id;
    private String name;
    private String college;//学院外键
    private String major;//专业名称
    private Integer grade;//年级名称
    private String edu;
}
