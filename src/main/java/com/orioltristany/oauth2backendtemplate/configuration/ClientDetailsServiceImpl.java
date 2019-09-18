package com.orioltristany.oauth2backendtemplate.configuration;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {
	private final Integer accessTokenValidity;
	private final Integer refreshTokenValidity;
	private final String clientId;
	private final String clientSecret;
	private final Logger logger = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

	@Autowired
	public ClientDetailsServiceImpl(
			@Value("${com.orioltristany.oauth2backendtemplate.accesstoken.validity}") Integer accessTokenValidity,
			@Value("${com.orioltristany.oauth2backendtemplate.refreshtoken.validity}") Integer refreshTokenValidity,
			@Value("${com.orioltristany.oauth2backendtemplate.clientid}") String clientId,
			@Value("${com.orioltristany.oauth2backendtemplate.clientsecret}") String clientSecret) {
		super();
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) {
		logger.debug("loadClientByClientId: " + clientId);
		if (!clientId.equals(this.clientId)) {
			throw new RuntimeException("bad clientId");
		}
		BaseClientDetails details = new BaseClientDetails();
		details.setClientId(clientId);
		details.setClientSecret(clientSecret);
		details.setAuthorizedGrantTypes(Arrays.asList("refresh_token", "password"));
		details.setScope(Arrays.asList("production"));
		details.setAccessTokenValiditySeconds(accessTokenValidity);
		details.setRefreshTokenValiditySeconds(refreshTokenValidity);
		return details;
	}
}