package com.services.tasknbudget.service;

import com.services.tasknbudget.dto.UserDTO;
import com.services.tasknbudget.entity.User;
import com.services.tasknbudget.mapper.UserMapper;
import com.services.tasknbudget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	public UserDTO registerUser(UserDTO userDTO) {
		User user = userMapper.toEntity(userDTO);
		// encode password before saving to database.
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return userMapper.toDto(userRepository.save(user));
	}
}
