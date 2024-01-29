package com.wust_hello.service.user;

import com.wust_hello.common.Result;
import com.wust_hello.dto.user.PasswordForm;

public interface UpdatePasswordService {
    Result update(PasswordForm form);
}
