package com.chunsik.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Document(collection = "post")
public class Post {

	@Id
	private long id;

	private String title;
	private String content;
	private String creatorId;
	private String creatorName;
	private String date;

}
