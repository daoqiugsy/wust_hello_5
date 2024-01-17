package com.wust_hello.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeekSummary {
    Integer id;
    LocalDate startTime;
    LocalDate endTime;
    String currentPlan;
    String nextPlan;
    Boolean isPush;
}
