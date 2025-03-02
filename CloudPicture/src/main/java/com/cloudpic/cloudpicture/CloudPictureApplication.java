package com.cloudpic.cloudpicture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.cloudpic.cloudpicture.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class CloudPictureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudPictureApplication.class, args);
	}

}
