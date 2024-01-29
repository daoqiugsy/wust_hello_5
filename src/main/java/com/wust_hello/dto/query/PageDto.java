package com.wust_hello.dto.query;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Data
public class PageDto<T> {
    private Integer total;
    private Integer pages;
    private List<T> list;
}
