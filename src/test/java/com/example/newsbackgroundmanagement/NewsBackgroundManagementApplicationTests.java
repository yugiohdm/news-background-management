package com.example.newsbackgroundmanagement;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.baomidou.mybatisplus.generator.config.OutputFile.mapper;

@EnableTransactionManagement
@SpringBootTest(classes = {NewsBackgroundManagementApplication.class})
class NewsBackgroundManagementApplicationTests {


	@Test
	void contextLoads() {

	}

}
