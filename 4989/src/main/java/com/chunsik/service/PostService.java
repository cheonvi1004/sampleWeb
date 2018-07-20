package com.chunsik.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunsik.entity.Post;
import com.chunsik.model.ParamModel;
import com.chunsik.repositroy.PostRepository;
import com.chunsik.repositroy.SequenceCustomRepository;

@Service
public class PostService {

	private final static String COLLECTION_SEQ = "post_seq";
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private SequenceCustomRepository sequenceCustomRepository;

	public Post saveArtice(ParamModel paramModel) {
		Post post = new Post();
		post.setId(sequenceCustomRepository.getNextSeqId(COLLECTION_SEQ));
		post.setTitle(paramModel.getTitle());
		post.setContent(paramModel.getContent());
		post.setCreatorId("tidehyun");
		post.setCreatorName("윤경환");
		post.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

		try {
			return postRepository.save(post);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Post getArticle(Long id) {
		return postRepository.findOne(id);
	}

}
