package com.wust_hello.service.student;

import com.wust_hello.dto.CollegeDto;
import com.wust_hello.dto.MajorDto;
import com.wust_hello.dto.StudentDto;
import com.wust_hello.model.College;
import com.wust_hello.model.Major;
import com.wust_hello.model.Student;

import java.util.List;

public interface PersonService {
    public StudentDto selectById(String token);
    public List<CollegeDto> allCollege();
    public List<MajorDto> selectByPageId(Long collegeId);
    public void updateById(Student student, String token);
}
