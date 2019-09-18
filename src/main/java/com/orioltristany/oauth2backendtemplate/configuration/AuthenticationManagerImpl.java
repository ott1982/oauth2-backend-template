package com.orioltristany.oauth2backendtemplate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager {
	private final AuthenticationProviderImpl authenticationProvider;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public AuthenticationManagerImpl(AuthenticationProviderImpl authenticationProvider) {
		super();
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		logger.trace("authenticate");
		return authenticationProvider.authenticate(authentication);
	}
}