package com.wust_hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

        /*打卡记录id*/
        private Long id;

        /*时间段*/
        private String range;
        /*打卡日期*/
        private String date;
        /*有效时间*/
        private Integer valid;
}
