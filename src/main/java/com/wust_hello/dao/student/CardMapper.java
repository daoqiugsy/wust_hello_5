package com.wust_hello.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wust_hello.dto.CardDto;
import com.wust_hello.model.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CardMapper extends BaseMapper<Card> {


    void add(Card card);

    List<Card> selectAllByStuId(Long userId);


}
