package com.wust_hello.service.user.impl;

import com.wust_hello.common.Result;
import com.wust_hello.dao.user.UserMapper;
import com.wust_hello.dto.user.PasswordForm;
import com.wust_hello.model.User;
import com.wust_hello.service.security.impl.UserDetailsImpl;
import com.wust_hello.service.user.UpdatePasswordService;
import com.wust_hello.util.JudgePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdatePasswordServiceImpl implements UpdatePasswordService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result update(PasswordForm form) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String phone = form.getPhone();
        String old_password = form.getOldPassword();
        String new_password = form.getNewPassword();

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user0 = loginUser.getUser();
        Long userid = user0.getId();

        // TODO 判断当前密码是否输入正确
        User user = userMapper.selectById(userid);
        if (!(bCryptPasswordEncoder.matches(old_password, user.getPassword()))) {
            return Result.error("当前密码输入错误");
        }

        // TODO 判断新密码是否与历史密码重复
        List<User> users = userMapper.selectLatestThree(phone);

        try {
            users.forEach(item -> {
                if (bCryptPasswordEncoder.matches(new_password, item.getPassword())) {
                    throw new RuntimeException("新密码与历史密码重复");
                }
            });
        } catch (Exception e) {
            return Result.error("新密码与历史密码重复");
        }

        // TODO 判断新密码是否合规
        JudgePassword judgePassword = new JudgePassword(new_password);
        try {
            if(!(judgePassword.hyperJudge())) {
                throw new RuntimeException("新密码不符合规则");
            }
        } catch (Exception e) {
            return Result.error("新密码不符合规则");
        }

        //TODO 创建新记录
        User user1 = new User();
        user1.setPhone(phone);
        user1.setRole(user.isRole());
        user1.setPassword(bCryptPasswordEncoder.encode(new_password));
        userMapper.insert(user1);

        return Result.success();
    }
}

