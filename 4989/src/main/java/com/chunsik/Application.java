package com.chunsik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.chunsik.config.advice.EnableMeesageSource;

@SpringBootApplication
@EnableMeesageSource(baseNames = { "classpath:i18n/messages" })
@EnableAspectJAutoProxy
@EnableRedisHttpSession
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
