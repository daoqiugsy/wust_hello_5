package com.wust_hello.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private Long id;
    private String name;
    private String college;//学院外键
    private Long collegeId;
    private String major;//专业名称
    private Long majorId;//专业名称
    private Integer grade;//年级名称
    private String edu;


}
