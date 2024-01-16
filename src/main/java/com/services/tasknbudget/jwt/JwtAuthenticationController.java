package com.services.tasknbudget.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

	private final JwtTokenService tokenService;

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationController(JwtTokenService tokenService,
	                                   AuthenticationManager authenticationManager) {
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest request) {
		try {
			var authenticationToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
			var authentication = authenticationManager.authenticate(authenticationToken);
			var token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new JwtTokenResponse(token));
		} catch (AuthenticationException ex) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}
}
