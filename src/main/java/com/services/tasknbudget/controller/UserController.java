package com.services.tasknbudget.controller;

import com.services.tasknbudget.dto.UserDTO;
import com.services.tasknbudget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
		if(userDTO != null) {
			UserDTO savedUser = userService.registerUser(userDTO);
			return ResponseEntity.ok(savedUser);
		}
		return ResponseEntity.badRequest().build();
	}
}
