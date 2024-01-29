package com.wust_hello.service.student.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.wust_hello.dao.student.CardMapper;
import com.wust_hello.dto.student.CardDto;
import com.wust_hello.model.Card;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wust_hello.model.PageBean;
import com.wust_hello.service.student.CardService;
import com.wust_hello.util.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Transactional
@Service
public class CardServiceImpl implements CardService {
    @Resource
    CardMapper cardMapper;
    @Override
    public PageBean selectAllByStuId(Integer pageNum, Integer pageSize,String token) {
        /*开启分页查询*/
        Long userId= TokenHandler.parseToken(token);
        PageHelper.startPage(pageNum, pageSize);
        List<Card> cardList = cardMapper.selectAllByStuId(userId);
        PageInfo<Card> page = new PageInfo<>(cardList);

        List<CardDto> cardDtos = new ArrayList<>();
        if(cardList != null && !cardList.isEmpty()){
            for (Card card : cardList) {
                CardDto dto = new CardDto();
                dto.setId(card.getId());
                /*将开始时间和结束时间转化为打卡时间段*/
                DateTime start = DateUtil.parse(card.getStartTime());
                DateTime end = DateUtil.parse(card.getEndTime());
                int start1 = start.getHours();
                int end1 = end.getHours();
                String range = start1 + "-" + end1;
                dto.setRange(range);
                dto.setDate(card.getDate());
                dto.setValid(card.getValid());
                cardDtos.add(dto);
            }
        }
        
        PageInfo<CardDto> pageInfo = new PageInfo<>(cardDtos);
        BeanUtils.copyProperties(page, pageInfo);
        PageInfo info = PageInfo.of(cardDtos);
        PageBean pageBean = new PageBean(pageInfo.getTotal(), info.getList());
        log.info(pageInfo.toString());
        log.info(info.toString());
        return pageBean;
    }

    @Override
    public void add(Card card, String token) {
        Long stuId= TokenHandler.parseToken(token);
        card.setId(IdUtil.getSnowflake(0,0).nextId());
        card.setStuId(stuId);
        /*手动填充更新修改时间*/
        LocalDateTime b = LocalDateTime.now();
        card.setCreateTime(b);
        card.setUpdateTime(b);
        cardMapper.add(card);
    }
}
