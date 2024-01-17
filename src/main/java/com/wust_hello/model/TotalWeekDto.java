package com.wust_hello.model;

import lombok.Data;

import java.util.List;

@Data
public class TotalWeekDto {
    Integer totalPage;
    List<WeekSummary> summaryList;
}
