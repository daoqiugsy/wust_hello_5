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
    LocalDate startTime;
    LocalDate endTime;
    String currentPlan;
    String nextPlan;
    Boolean isPush;
    String sentiment;
}
