package com.wust_hello.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class PageQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private Boolean isAsc;
}
