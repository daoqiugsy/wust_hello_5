package com.wust_hello.service.user;

import com.wust_hello.common.Result;
import com.wust_hello.dto.user.RegisterForm;
import com.wust_hello.model.User;

public interface RegisterService {
    Result register(RegisterForm stu);
}
