package com.wust_hello.service.student.impl;

import com.wust_hello.dao.student.PersonMapper;
import com.wust_hello.dto.CollegeDto;
import com.wust_hello.dto.MajorDto;
import com.wust_hello.dto.StudentDto;
import com.wust_hello.model.College;
import com.wust_hello.model.Major;
import com.wust_hello.model.Student;
import com.wust_hello.service.student.PersonService;
import com.wust_hello.util.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    PersonMapper personMapper;
    /*通过ID查询学生个人信息*/
    public StudentDto selectById(String token) {
        Long stuId= TokenHandler.parseToken(token);
        Student student = personMapper.selectById(stuId);
        String college = personMapper.selectByCollegeId(student.getCollegeId()).getName();
        String major = personMapper.selectByMajorId(student.getMajorId()).getName();
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getName(),
                college,
                major,
                student.getGrade(),
                student.getEdu());
        return studentDto;
    }

    public List<CollegeDto> allCollege() {
        List<College> collegeList = personMapper.allCollege();
        List<CollegeDto> collegeDtos = new ArrayList<>();

        for (College college : collegeList) {
            CollegeDto dto = new CollegeDto();
            dto.setId(college.getId());
            dto.setName(college.getName());
            collegeDtos.add(dto);

        }
        log.info(collegeDtos.toString());

        return collegeDtos;
    }

    public List<MajorDto> selectByPageId(Long collegeId) {
        List<Major> majorList = personMapper.selectByPageId(collegeId);
        List<MajorDto> majorDtos = new ArrayList<>();
        for (Major major : majorList) {
            MajorDto dto = new MajorDto();
            dto.setId(major.getId());
            dto.setName(major.getName());
            majorDtos.add(dto);

        }
        return majorDtos;
    }

    public void updateById(Student student, String token) {
        Long stuId= TokenHandler.parseToken(token);
        LocalDateTime time = LocalDateTime.now();
        student.setUpdateTime(time);

        personMapper.updateById(student, stuId);
    }
}
