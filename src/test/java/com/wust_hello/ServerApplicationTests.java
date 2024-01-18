package com.wust_hello;

import com.wust_hello.util.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ServerApplicationTests {

	@Test
	void contextLoads() {
		String token=TokenHandler.genAccessToken(1L);
		log.info(token);
	}

}
