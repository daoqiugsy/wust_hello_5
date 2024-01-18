package com.wust_hello.service.student.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wust_hello.dao.student.LeisureMapper;
import com.wust_hello.model.Leisure;
import com.wust_hello.service.student.LeisureService;
import org.springframework.stereotype.Service;

@Service
public class LeisureServiceImpl extends ServiceImpl<LeisureMapper, Leisure> implements LeisureService {
}
