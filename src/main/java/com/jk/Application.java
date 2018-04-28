package com.jk;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class Application {

	public static void main(String[] args) {
		//测试2
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Queue queue(){
		return new Queue("test");
	}
}
