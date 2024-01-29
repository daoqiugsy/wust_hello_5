package com.wust_hello.controller.user;

import com.wust_hello.common.Result;
import com.wust_hello.dto.user.PasswordForm;
import com.wust_hello.model.User;
import com.wust_hello.service.user.LoginService;
import com.wust_hello.service.user.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UpdatePasswordService updatePasswordService;

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


    @GetMapping("/user/test")
    public String test() {
        return "success";
    }
}
