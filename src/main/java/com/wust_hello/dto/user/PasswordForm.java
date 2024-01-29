package com.wust_hello.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {
    private String phone;
    private String oldPassword;
    private String newPassword;
}

