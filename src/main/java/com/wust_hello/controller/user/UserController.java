package com.wust_hello.controller.user;

import com.wust_hello.common.Result;
import com.wust_hello.dto.user.PasswordForm;
import com.wust_hello.dto.user.RegisterForm;
import com.wust_hello.model.User;
import com.wust_hello.service.user.LoginService;
import com.wust_hello.service.user.RegisterService;
import com.wust_hello.service.user.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UpdatePasswordService updatePasswordService;

    @Autowired
    private RegisterService registerService;


    @PostMapping("/user/authenticateUser")
    public Result login(@RequestBody User user) {
        //登录
        return loginService.login(user);
    }


    @PostMapping("/student/updatePassword")
    public Result changePassword(@RequestBody PasswordForm form) {
        //修改密码
        return updatePasswordService.update(form);
    }


    @PostMapping("/user/register")
    public Result register(@RequestBody RegisterForm stu) {
        //注册
        return registerService.register(stu);
    }


    @GetMapping("/user/test")
    public String test() {
        return "success";
    }
}
