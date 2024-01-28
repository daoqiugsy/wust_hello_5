package com.wust_hello;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.wust_hello.mapper.CardMapper;
import com.wust_hello.mapper.LeisureMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    @Test
    public void testMybatis() throws IOException {
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


    }
}

