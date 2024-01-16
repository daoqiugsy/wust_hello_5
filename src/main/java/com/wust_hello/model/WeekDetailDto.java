package com.wust_hello.model;

import java.time.LocalDate;

public class WeekDetailDto {
    Integer id;
    LocalDate startTime;
    LocalDate endTime;
    String currentPlan;
    String nextPlan;
    Boolean isPush;
    String sentiment;
}
