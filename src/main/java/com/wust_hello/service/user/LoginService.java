package com.wust_hello.service.user;

import com.wust_hello.common.Result;
import com.wust_hello.model.User;

public interface LoginService {
    Result login(User user);
}
