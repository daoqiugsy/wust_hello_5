package com.wust_hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String phone;
    private String password;
    private int role;
    private String update_time;
    private String create_time;
    private int delete;

}
