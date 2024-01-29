package com.wust_hello.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wust_hello.dto.student.CardBueaty;
import com.wust_hello.model.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CardMapper extends BaseMapper<Card> {


    void add(Card card);

    List<CardBueaty> selectCardGroup(@Param("userId") Long userId);

    List<Card> selectByDate(Long userId, String date);
}
