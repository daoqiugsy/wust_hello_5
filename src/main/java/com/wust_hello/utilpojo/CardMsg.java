package com.wust_hello.utilpojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardMsg {
    private String name;
    private String date;
    private String start_time;
    private String end_time;
    private int valid;
}
