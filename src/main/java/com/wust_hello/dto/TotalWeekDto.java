package com.wust_hello.dto;

import lombok.Data;

import java.util.List;

@Data
public class TotalWeekDto {
    Long totalPage;
    List<WeekSummary> summaryList;
}
