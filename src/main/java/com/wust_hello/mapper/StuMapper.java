package com.wust_hello.mapper;

import com.wust_hello.model.Student;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface StuMapper {
    public List<Student> list(String name);
}

