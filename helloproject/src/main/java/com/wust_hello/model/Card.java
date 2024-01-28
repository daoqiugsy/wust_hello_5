package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    private Long id;
    private Long stu_id;
    private String start_time;
    private String end_time;
    private String date;
    private int valid;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private boolean deleted;


}
