package com.wust_hello.dto.student;

import lombok.Data;

@Data
public class LeisureModifyDto {
    Boolean thisWeek;
    Short day;
    Short time;
    Short status;
}
