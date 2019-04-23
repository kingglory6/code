package com.newer.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.newer.mall.common.mapper")
@SpringBootApplication
@EnableScheduling
public class MallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}

}
