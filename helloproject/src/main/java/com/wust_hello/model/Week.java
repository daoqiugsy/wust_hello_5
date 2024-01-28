package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Week {
    private int id;
    private int stu_id;
    private String start_time;
    private String end_time;
    private String current_plan;
    private String next_plan;
    private int is_push;
    private String sentiment;
    private String create_time;
    private String update_time;
    private int delete;
}
