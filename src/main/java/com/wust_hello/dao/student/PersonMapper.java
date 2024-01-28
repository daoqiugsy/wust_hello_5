package com.wust_hello.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wust_hello.dto.CollegeDto;
import com.wust_hello.dto.StudentDto;
import com.wust_hello.model.College;
import com.wust_hello.model.Major;
import com.wust_hello.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface PersonMapper extends BaseMapper<Student> {

    Student selectById(Long id);

    List<College> allCollege();

    List<Major> selectByPageId(Long collegeId);

    void updateById(@Param("student") Student student, @Param("stuId") Long stuId);

    College selectByCollegeId(Long collegeId);

    Major selectByMajorId(Long majorId);
}
