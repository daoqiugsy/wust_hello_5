package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Major {
    private Long id;
    private String name;
    private long college_id;
    private String create_time;
    private String update_time;
    private int delete;
}
