package com.chunsik.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import com.chunsik.config.advice.EnableMeesageSource;

@Configuration
public class MessageSourceConfig implements ImportAware {

	private String defaultEncoding;
	private String[] baseNames;

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(this.baseNames);
		messageSource.setDefaultEncoding(this.defaultEncoding);
		messageSource.setAlwaysUseMessageFormat(true);
		messageSource.setCacheSeconds(60);
		return messageSource;
	}

	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(this.messageSource());
	}

	@Override
	public void setImportMetadata(AnnotationMetadata am) {
		AnnotationAttributes aa = AnnotationAttributes
				.fromMap(am.getAnnotationAttributes(EnableMeesageSource.class.getName()));
		this.defaultEncoding = aa.getString("defaultEncoding");
		this.baseNames = aa.getStringArray("baseNames");
	}

}
