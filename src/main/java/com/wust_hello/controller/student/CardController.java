package com.wust_hello.controller.student;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.wust_hello.model.Card;
import com.wust_hello.model.PageBean;
import com.wust_hello.model.Result;
import com.wust_hello.service.student.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/student/time")
public class CardController {
    @Autowired(required = false)
    private CardService cardService;

    /*根据学生id查询该学生所有打卡记录*/
    /*分页查询*/
    @GetMapping("/daliyrecord")
    public Result selectAllByStuId(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   HttpServletRequest request){
        /*返回带有分页信息的数据*/
        PageBean info = cardService.selectAllByStuId(pageNum, pageSize, request.getHeader("token"));
        if (info == null) {
            return Result.error("查询失败");
        }
        return Result.success(info);
    }

    @PostMapping("/card/submit")
    public Result add(@RequestBody Card card,
                      HttpServletRequest request) {
        /*校验有效工时是否合法*/
        DateTime start = DateUtil.parse(card.getStartTime());
        DateTime end = DateUtil.parse(card.getEndTime());
        long start_timestamp = start.getTime();
        long end_timestamp = end.getTime();
        double valid = (double)(end_timestamp - start_timestamp) / 3600000 ;
        if(card.getValid() > valid){
            return Result.error("有效工时输入不合法，请重新输入");
        }

        cardService.add(card, request.getHeader("token"));
        return Result.success();
    }


}
