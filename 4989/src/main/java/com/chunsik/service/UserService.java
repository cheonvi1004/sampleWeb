package com.chunsik.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunsik.entity.Role;
import com.chunsik.entity.User;
import com.chunsik.model.UserModel;
import com.chunsik.repositroy.SequenceCustomRepository;
import com.chunsik.repositroy.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private final static String COLLECTION_SEQ = "user_seq";
	@Autowired
	private SequenceCustomRepository sequenceCustomRepository;
	@Autowired
	private UserRepository userRepository;

	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	public User create(UserModel userModel) {
		User user = User.build(sequenceCustomRepository.getNextSeqId(COLLECTION_SEQ), userModel.getEmail(),
				userModel.getPassword(), userModel.getName(), Role.USER);
		return userRepository.save(user);
	}

}
