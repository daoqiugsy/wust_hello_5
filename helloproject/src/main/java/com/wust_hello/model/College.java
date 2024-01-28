package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class College {
    private Long id;
    private String name;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private boolean deleted;
}
