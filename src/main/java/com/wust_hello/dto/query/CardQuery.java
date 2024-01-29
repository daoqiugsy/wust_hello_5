package com.wust_hello.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardQuery extends PageQuery {
    private String name;
    private LocalDate date;
    private String period;
    private Double valid;
}
