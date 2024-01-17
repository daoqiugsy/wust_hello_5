package com.wust_hello.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wust_hello.dao.LeisureMapper;
import com.wust_hello.model.Leisure;
import com.wust_hello.service.LeisureService;
import org.springframework.stereotype.Service;

@Service
public class LeisureServiceImpl extends ServiceImpl<LeisureMapper, Leisure> implements LeisureService {
}
