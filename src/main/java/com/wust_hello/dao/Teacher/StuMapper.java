package com.wust_hello.dao.Teacher;

import com.wust_hello.dto.StudentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StuMapper {
    public List<StudentDto> list(String name);

    public List<StudentDto> findAll();
    @Select("select * from user where id=#{id} and role=1")
    public Boolean isgetid(Long id);
}

