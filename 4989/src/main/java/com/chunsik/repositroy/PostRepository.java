package com.chunsik.repositroy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chunsik.entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, Long> {

}
