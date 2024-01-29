package com.wust_hello.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JudgePassword {

    private String password;
    /**
     * 正则-密码强度-基础要求, 数字
     */
    public static final String REGEX_PASSWORD_STRENGTH_BASIC = "^(?=.*[0-9]).+$";
    /**
     * 正则-密码强度-大小写字母
     */
    public static final String REGEX_PASSWORD_STRENGTH_UPPER_CHAR = "^[A-Za-z]+$";

    /**
     * 正则-密码强度-至少一位符号
     */
    public static final String REGEX_PASSWORD_STRENGTH_CHARS = "^(?=.*[`~!@#$%^&*()_\\-+=<>?:\"{}|,./;'\\[\\]·~！@#￥%……&*（）——\\-+={}|《》？：“”【】、；‘'，。、]).+$";

    //密码强度检查


    public boolean judgeNum() {
        return this.password.matches(REGEX_PASSWORD_STRENGTH_BASIC);
    }

    public boolean judgeChar() {
        return this.password.matches(REGEX_PASSWORD_STRENGTH_UPPER_CHAR);
    }

    public boolean judgeSPChar() {
        return this.password.matches(REGEX_PASSWORD_STRENGTH_CHARS);
    }

    public boolean hyperJudge() {
        if(password.length() < 8) {
            return false;
        } else if (!(this.judgeChar()) || !(this.judgeNum()) || !(this.judgeSPChar())) {
            return true;
        }
        return false;
    }
}