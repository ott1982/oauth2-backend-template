package com.orioltristany.oauth2backendtemplate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.trace("configure-HttpSecurity");
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

	}
}