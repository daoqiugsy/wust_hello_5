package com.wust_hello.service.student;

import com.wust_hello.dto.student.CollegeDto;
import com.wust_hello.dto.student.MajorDto;
import com.wust_hello.dto.student.StudentDto;
import com.wust_hello.model.Student;

import java.util.List;

public interface PersonService {
    public StudentDto selectById(String token);
    public List<CollegeDto> allCollege();
    public List<MajorDto> selectByPageId(Long collegeId);
    public void updateById(Student student, String token);
}
