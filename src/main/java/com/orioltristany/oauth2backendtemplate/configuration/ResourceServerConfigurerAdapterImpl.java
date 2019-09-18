package com.orioltristany.oauth2backendtemplate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfigurerAdapterImpl extends ResourceServerConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void configure(HttpSecurity http) throws Exception {
		logger.trace("configure-HttpSecurity");

		// ONLY SECURIZES THIS END POINT. OTHERS ARE OPENED.
		http.authorizeRequests().antMatchers("/securized").hasAnyAuthority("USER");

		// IT OPENS END POINT IF IT'S FOLLOWED BY GLOBAL SECURIZATION
		http.authorizeRequests().antMatchers("/unsecurized").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}
}