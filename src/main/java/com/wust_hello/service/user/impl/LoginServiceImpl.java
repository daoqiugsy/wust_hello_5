package com.wust_hello.service.user.impl;

import com.wust_hello.common.Result;
import com.wust_hello.model.User;
import com.wust_hello.service.security.impl.UserDetailsImpl;
import com.wust_hello.service.user.LoginService;
import com.wust_hello.util.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    //
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Result login(User user) {
        //AuthenticationManager authenticate进行用户认证

        //将手机号密码封装成一个authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getPhone(), user.getPassword());

        //让AuthenticationManager帮助我们去验证这个authentication对象(TODO 去调用UserDetailService)
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        try {
            if(Objects.isNull(authenticate)) {
                throw new RuntimeException("登录失败");
            }
        } catch (Exception e) {
            return Result.error("用户名或密码错误");
        }

        //如果认证通过了，使用userid生成一个jwt jwt存入Result返回
        //强制类型转换获取我们的user对象
        UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
        Long userId = principal.getUser().getId();
        Boolean role = principal.getUser().isRole();
        String jwtToken = TokenHandler.genAccessToken(userId);
        String level = role ? "Teacher" : "Student";
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("level", level);

        return Result.success(map);
    }
}
