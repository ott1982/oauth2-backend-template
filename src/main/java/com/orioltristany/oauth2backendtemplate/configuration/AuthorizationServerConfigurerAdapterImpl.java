package com.orioltristany.oauth2backendtemplate.configuration;

import java.nio.file.Paths;
import java.security.KeyPair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurerAdapterImpl extends AuthorizationServerConfigurerAdapter {
	private final String keystorePath;
	private final String keystoreAlias;
	private final String keystorePassword;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final ClientDetailsServiceImpl clientDetailsService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public AuthorizationServerConfigurerAdapterImpl(
			@Value("${com.orioltristany.oauth2backendtemplate.keystore.path}") String keystorePath,
			@Value("${com.orioltristany.oauth2backendtemplate.keystore.alias}") String keystoreAlias,
			@Value("${com.orioltristany.oauth2backendtemplate.keystore.password}") String keystorePassword,
			AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			ClientDetailsServiceImpl clientDetailsService) {
		super();
		this.keystorePath = keystorePath;
		this.keystoreAlias = keystoreAlias;
		this.keystorePassword = keystorePassword;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.clientDetailsService = clientDetailsService;
	}

	public JwtAccessTokenConverterImpl jwtAccessTokenConverter() {
		logger.trace("jwtAccessTokenConverter");
		JwtAccessTokenConverterImpl converter = new JwtAccessTokenConverterImpl();
		KeyPair keyPair = new KeyStoreKeyFactory(new FileSystemResource(Paths.get(keystorePath)),
				keystorePassword.toCharArray()).getKeyPair(keystoreAlias);
		converter.setKeyPair(keyPair);
		return converter;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
		logger.trace("configure-ClientDetailsServiceConfigurer");
		clientDetailsServiceConfigurer.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		logger.trace("configure-AuthorizationServerEndpointsConfigurer");
		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
				.accessTokenConverter(jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		logger.trace("configure-AuthorizationServerSecurityConfigurer");
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
}