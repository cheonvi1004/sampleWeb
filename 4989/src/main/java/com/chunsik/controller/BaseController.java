package com.chunsik.controller;

import org.springframework.beans.factory.annotation.Value;

public class BaseController {

	protected final static String LAYOUT_MAIN_CONTENT = "LAYOUT_MAIN_CONTENT";

	@Value("${config.freemarker.layout:layout}")
	protected String layout;

}
