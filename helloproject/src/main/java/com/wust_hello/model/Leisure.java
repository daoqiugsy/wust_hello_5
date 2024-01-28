package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Leisure{
    private Long id;
    private int time_slot;
    private int is_leisure;
    private String date;
    private long stu_id;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private boolean deleted;
}
