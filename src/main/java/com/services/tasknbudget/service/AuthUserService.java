package com.services.tasknbudget.service;

import com.services.tasknbudget.entity.User;
import com.services.tasknbudget.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		// Convert roles/authorities when implemented for now return empty list.
//		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName()))
//				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails
				.User(user.getUsername(), user.getPassword(), Collections.emptyList());
	}

}
