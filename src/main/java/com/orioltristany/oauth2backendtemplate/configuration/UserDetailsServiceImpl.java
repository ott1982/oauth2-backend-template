package com.orioltristany.oauth2backendtemplate.configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDetails loadUserByUsername(String username) {
		logger.trace("loadUserByUsername");
		boolean enabled = true;
		boolean isNotExpiredAccount = true;
		boolean isCredentialsNotExpired = true;
		boolean isAccountNotLocked = true;
		UserDetails obj = new User(username, "", enabled, isNotExpiredAccount, isCredentialsNotExpired,
				isAccountNotLocked, getAuthorithies(username));
		return obj;
	}

	private List<GrantedAuthority> getAuthorithies(String username) {
		return Stream.of(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"))
				.collect(Collectors.toList());
	}
}
