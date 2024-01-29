package com.wust_hello.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.wust_hello.dao.Teacher.CardMapper;
import com.wust_hello.dto.query.CardQuery;
import com.wust_hello.dto.query.PageDto;
import com.wust_hello.mapper.LeisureMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.wust_hello.utilpojo.CardMsg;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class TeacherController {

    //加载核心配置文件
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
    //获取SqlSessionFactoryBuilder
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    //获取SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    //获取sqlsession（代表Java程序和数据库之间的会话）
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //获取mapper接口对象
    CardMapper mapper = sqlSession.getMapper(CardMapper.class);
    LeisureMapper mapper1 = sqlSession.getMapper(LeisureMapper.class);

    public TeacherController() throws IOException {
    }

    //所有学生打卡信息查询
    @GetMapping("/teacher/getall")
    public List<CardMsg> findAllMsg(int pageSize,int pageNum){
        int startData,endData;
        startData=pageSize*(pageNum-1);
        endData=pageSize;
        return mapper.findAllCardMsg(pageSize,pageNum,startData,endData);
    }


    //学生打卡信息查询
    @GetMapping("/teacher/getmessage")
    List<CardMsg> findCardMsg(int pageSize,String name,int pageNum){
        int startData,endData;
        startData=pageSize*(pageNum-1);
        endData=pageSize;
        return mapper.findCardMsg(pageSize,name,pageNum,startData,endData);
    }

    //按照打卡日期正序排列（从前至今）
    @GetMapping("/teacher/sortdate")
    List<CardMsg> sortDateCardMsg(int pageSize,int pageNum){
        int startData,endData;
        startData=pageSize*(pageNum-1);
        endData=pageSize;
        return mapper.sortDateCardMsg(pageSize,pageNum,startData,endData);
    }

    //空闲人数查询
    @GetMapping("/teacher/getfreenumber")
    int[][] selectLeisure(int index) throws ParseException {
        //将日期转化为星期
        Date d=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //date表示日期
        String date=formatter.format(d);
        String rq = date;
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(rq);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        //w表示星期几
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1 < 0 ? 0 : cal.get(Calendar.DAY_OF_WEEK) - 1;

        int[][] a = new int[9][5];
        int temp;
        //id==1表示本周，id==0表示下周
        if(index==1){
            temp=0;
        }
        else{
            temp=7;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                cal.add(Calendar.DATE,j+1-w+temp);
                //w表示星期几
                String newDate=formatter.format(cal.getTime());
                a[i][j] = mapper1.selectLeisure(i, newDate);
                cal.add(Calendar.DATE,-(j+1-w+temp));
            }
        }
        return a;
    }

}
