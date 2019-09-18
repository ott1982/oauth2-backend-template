package com.orioltristany.oauth2backendtemplate.configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Authentication authenticate(final Authentication authentication) {
		if (logger.isTraceEnabled()) {
			logger.trace("authenticate: {}", ReflectionToStringBuilder.toString(authentication));
		}
		String username = authentication.getPrincipal().toString();
		String expectedPassword = getExpectedPassword(username);
		authenticate(authentication.getCredentials().toString(), expectedPassword);
		return new UsernamePasswordAuthenticationToken(username, null, getGrantedAuths(username));
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		logger.trace("supports");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private String getExpectedPassword(String username) {
		logger.trace("getExpectedPassword");
		// TODO: apply JPA or alternatives
		return "mypassword";
	}

	private void authenticate(String password, String expectedPassword) {
		logger.trace("authenticate");
		// TODO: apply BCrypt
		if (!password.equals(expectedPassword)) {
			throw new RuntimeException("bad user password");
		}
	}

	private List<GrantedAuthority> getGrantedAuths(final String username) {
		logger.trace("getGrantedAuths");
		// TODO: apply JPA
		return Stream.of(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"))
				.collect(Collectors.toList());
	}
}
