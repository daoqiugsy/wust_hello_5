package com.wust_hello.service.student;

import com.wust_hello.model.Card;
import com.wust_hello.model.PageBean;

public interface CardService {
//    分页查询学生打卡信息
    public PageBean selectAllByStuId(Integer pageNum, Integer pageSize, String token);
//    打卡
    public void add(Card card, String token);
}
