package com.wust_hello.dto.student;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeekSummary {
    Long id;
    LocalDate startTime;
    LocalDate endTime;
    String currentPlan;
    String nextPlan;
    Boolean isPush;
}
