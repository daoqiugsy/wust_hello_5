package com.wust_hello.controller.student;

import com.wust_hello.dto.CollegeDto;
import com.wust_hello.dto.MajorDto;
import com.wust_hello.dto.StudentDto;
import com.wust_hello.model.Result;
import com.wust_hello.model.Student;
import com.wust_hello.service.student.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student")
public class PersonController {
    @Autowired
    PersonService personService;

    /*查询学生个人信息*/
    @GetMapping("/person")
    public Result person(HttpServletRequest request){

        StudentDto student = personService.selectById(request.getHeader("token"));
        return Result.success(student);
    }

    /*修改学生个人信息*/
    @PutMapping("/person/update")
    public Result updateById(@RequestBody Student student,
                             HttpServletRequest request) {

        personService.updateById(student, request.getHeader("token"));
        return Result.success();
    }

    /*查询所有学院*/
    @GetMapping("/person/college")
    public Result college(){

        List<CollegeDto> collegeList = personService.allCollege();

        return Result.success(collegeList);
    }

    /*根据学院ID查询专业信息*/
    @GetMapping("/person/selectByCollegeId")
    public Result selectByCollegeId(@RequestParam Long collegeId) {
        List<MajorDto> majorList = personService.selectByPageId(collegeId);
        return Result.success(majorList);
    }

}
