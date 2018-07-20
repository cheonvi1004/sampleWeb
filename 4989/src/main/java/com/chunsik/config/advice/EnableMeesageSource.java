package com.chunsik.config.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.chunsik.config.MessageSourceConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MessageSourceConfig.class)
public @interface EnableMeesageSource {
	String defaultEncoding() default "UTF-8";

	String[] baseNames() default { "classpath:i18n/message" };
}
