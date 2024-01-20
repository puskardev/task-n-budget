package com.services.tasknbudget.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.services.tasknbudget.service.AuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@Profile("prod")
@EnableWebSecurity
public class JwtSecurityConfig {

	private static final String[] AUTH_WHITELIST = {
			// -- swagger ui
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/v3/api-docs/**",
			"/webjars/**",
			// -- controller endpoints
			"/authenticate",
			"/register"
	};

	private final AuthUserService authUserService;

	public JwtSecurityConfig(AuthUserService authUserService) {
		this.authUserService = authUserService;
	}

	// Create an AuthenticationManager bean that will be used to authenticate users.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// Configure Spring Security to use JWT tokens and disable CSRF protection
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests((requests) -> {
					requests.requestMatchers(AUTH_WHITELIST).permitAll()
							.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
							.anyRequest().authenticated();
				})
				.csrf(AbstractHttpConfigurer::disable).sessionManagement((session) -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.oauth2ResourceServer((oauth2) -> {
					oauth2.jwt(Customizer.withDefaults());
				})
				.httpBasic(Customizer.withDefaults()).headers(
						header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
				.build();
	}

	// Create a NimbusJwtEncoder bean that will be used to encode JWT tokens.
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) throws JOSEException {
		return new NimbusJwtEncoder(jwkSource);
	}

	// Create a NimbusJwtDecoder bean that will be used to decode JWT tokens.
	@Bean
	public JwtDecoder jwtDecoder() throws JOSEException {
		return NimbusJwtDecoder
				.withPublicKey(rsaKey().toRSAPublicKey())
				.build();
	}

	// Create a JWKSource bean that will expose the RSA Key created below.
	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		JWKSet jwkSet = new JWKSet(rsaKey());

		return (((jwkSelector, securityContext)
				-> jwkSelector.select(jwkSet)));
	}

	// Create RSA key object using the key pair. This key object will be used to sign the JWT tokens.
	@Bean
	public RSAKey rsaKey() {
		KeyPair keyPair = keyPair();
		return new RSAKey
				.Builder((RSAPublicKey) keyPair.getPublic())
				.privateKey((RSAPrivateKey) keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString())
				.build();
	}

	// Create an RSA key pair to sign the JWT tokens
	@Bean
	public KeyPair keyPair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new IllegalStateException(
					"Unable to generate an RSA Key Pair", e);
		}
	}
}
