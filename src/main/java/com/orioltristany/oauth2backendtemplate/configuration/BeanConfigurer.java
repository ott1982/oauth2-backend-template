package com.orioltristany.oauth2backendtemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfigurer {

	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO: use BCrypt or alternatives
		return new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return new StringBuilder(rawPassword).toString().equals(encodedPassword);
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return new StringBuilder(rawPassword).toString();
			}
		};
	}

}
