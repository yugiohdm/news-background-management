package com.example.newsbackgroundmanagement;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.example.newsbackgroundmanagement.mapper")
public class NewsBackgroundManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsBackgroundManagementApplication.class, args);
	}

}
