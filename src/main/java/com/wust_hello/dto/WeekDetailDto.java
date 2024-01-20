package com.wust_hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekDetailDto {
    Integer id;
    String name;
    LocalDate start_time;
    LocalDate end_time;
    String current_plan;
    String next_plan;
    Boolean is_push;
    String sentiment;
}
