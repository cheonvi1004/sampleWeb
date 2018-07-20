package com.chunsik.repositroy;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chunsik.entity.User;

public interface UserRepository extends MongoRepository<User, Long> {

	Optional<User> findOneByEmail(String email);

}
