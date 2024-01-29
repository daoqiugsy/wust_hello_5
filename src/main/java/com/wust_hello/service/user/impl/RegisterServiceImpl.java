package com.wust_hello.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wust_hello.common.Result;
import com.wust_hello.dao.student.PersonMapper;
import com.wust_hello.dao.user.UserMapper;
import com.wust_hello.dto.user.RegisterForm;
import com.wust_hello.model.Student;
import com.wust_hello.model.User;
import com.wust_hello.service.user.RegisterService;
import com.wust_hello.util.JudgePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PersonMapper stuMapper;

    @Override
    public Result register(RegisterForm stu) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String phone = stu.getPhone();
        String name = stu.getName();
        String password = stu.getPassword();

        /**
         * @description: 判断用户是否存在
         * @author
         * @date 2024/1/29 20:17
         */

        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getId).eq(User::getPhone, phone);

        try{
            if(userMapper.selectList(userQueryWrapper).size() != 0) {
                throw new RuntimeException("用户已存在");
            };
        } catch (Exception e) {
            return Result.error("用户已存在");
        }

        /**
         * @description: 校验阶段
         * @author Ctungi
         * @date 2024/1/29 20:10
         */

        JudgePassword judgePhone = new JudgePassword(phone);
        JudgePassword judgePassword = new JudgePassword(password);

        try {
            if(! (judgePhone.judgePhone())) {
                throw new RuntimeException("手机号错误");
            }
        } catch (Exception e) {
            return Result.error("请填写正确的手机号");
        }

        try {
            if(!(judgePassword.hyperJudge())) {
                throw new RuntimeException("新密码不符合规则");
            }
        } catch (Exception e) {
            return Result.error("密码不符合规则");
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(false);
        userMapper.insert(user);

        Student student = new Student();
        student.setName(name);
        stuMapper.insert(student);

        return Result.success();

    }
}
