package com.wust_hello.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {


        /*时间段*/
        private String range;
        /*打卡日期*/
        private String date;
        /*有效时间*/
        private Integer valid;
}
