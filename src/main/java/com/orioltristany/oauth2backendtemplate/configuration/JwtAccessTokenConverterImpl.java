package com.orioltristany.oauth2backendtemplate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class JwtAccessTokenConverterImpl extends JwtAccessTokenConverter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		logger.trace("enhance");
		return super.enhance(accessToken, authentication);
	}
}
