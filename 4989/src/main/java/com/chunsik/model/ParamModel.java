package com.chunsik.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ParamModel {

	@NotEmpty(message = "ERR_TITLE_NULL")
	String title;
	@NotEmpty(message = "ERR_CONTENT_NULL")
	String content;

}
