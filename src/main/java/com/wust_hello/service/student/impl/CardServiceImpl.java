package com.wust_hello.service.student.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageInfo;
import com.wust_hello.dao.student.CardMapper;

import com.wust_hello.dto.student.CardBueaty;
import com.wust_hello.dto.student.CardDto;
import com.wust_hello.model.Card;
import com.github.pagehelper.PageHelper;
import com.wust_hello.model.PageBean;
import com.wust_hello.service.student.CardService;
import com.wust_hello.util.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class CardServiceImpl implements CardService {
    @Autowired(required = false)
    CardMapper cardMapper;

    @Override
    public PageBean selectAllByStuId(Integer pageNum, Integer pageSize, String token) {
        /*开启分页查询*/
        Long userId = TokenHandler.parseToken(token);
        PageHelper.startPage(pageNum, pageSize);
        List<CardBueaty> cardGroup = cardMapper.selectCardGroup(userId);
        PageInfo<CardBueaty> page = new PageInfo<>(cardGroup);
        List<CardDto> cardDtos = new ArrayList<>();
        if (cardGroup != null && !cardGroup.isEmpty()) {
            for (CardBueaty card : cardGroup) {
                CardDto dto = new CardDto();
                dto.setDate(card.getDate());
                dto.setValid(card.getValid());
                /*将开始时间和结束时间转化为打卡时间段*/
                List<Card> dateGroup = cardMapper.selectByDate(userId, card.getDate());
                String period = "";
                for (Card card1 : dateGroup) {
                    DateTime start = DateUtil.parse(card1.getStartTime());
                    DateTime end = DateUtil.parse(card1.getEndTime());
                    int start1 = start.getHours();
                    int end1 = end.getHours();
                    String range = start1 + "-" + end1;
                    period = period + range + " ";
                }
                dto.setRange(period);
                cardDtos.add(dto);

            }
        }
//        log.info("________"+cardDtos.toString());
        PageInfo<CardDto> pageInfo = new PageInfo<>(cardDtos);
        BeanUtils.copyProperties(page, pageInfo);
        PageInfo info = PageInfo.of(cardDtos);
        PageBean pageBean = new PageBean(pageInfo.getTotal(), info.getList());
//        log.info(pageInfo.toString());
//        log.info(info.toString());
        return pageBean;
    }

    @Override
    public void add(Card card, String token) {
        Long stuId = TokenHandler.parseToken(token);
        card.setId(IdUtil.getSnowflake(0, 0).nextId());
        card.setStuId(stuId);
        /*手动填充更新修改时间*/
        LocalDateTime b = LocalDateTime.now();
        card.setCreateTime(b);
        card.setUpdateTime(b);
        cardMapper.add(card);
    }
}
