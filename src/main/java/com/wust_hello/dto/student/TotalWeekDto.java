package com.wust_hello.dto.student;

import lombok.Data;

import java.util.List;

@Data
public class TotalWeekDto {
    Long totalPage;
    List<WeekSummary> summaryList;
}
