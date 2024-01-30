package com.wust_hello.service.security.filter;

import com.wust_hello.dao.user.UserMapper;
import com.wust_hello.model.User;
import com.wust_hello.service.security.impl.UserDetailsImpl;
import com.wust_hello.util.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token) || token.equals("null")) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }

//        解析token
        Long userid = TokenHandler.parseToken(token);
        User user = userMapper.selectById(userid);

        if(user == null) {
            throw new RuntimeException("用户不存在");
        }

        UserDetailsImpl loginUser = new UserDetailsImpl(user);

        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
