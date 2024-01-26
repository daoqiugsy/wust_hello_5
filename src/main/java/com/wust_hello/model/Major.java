package com.wust_hello.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Major {
    private Long id;
    private String name;

    @TableField(fill= FieldFill.INSERT)
    LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;
    @TableField(fill=FieldFill.INSERT)
    Boolean deleted;
}
