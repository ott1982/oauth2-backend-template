package com.orioltristany.oauth2backendtemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/securized")
public class SecurizedController {

	@GetMapping
	public ResponseEntity<String> get() {	
		return new ResponseEntity<>(
				"securized. authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities(),
				HttpStatus.OK);
	}

}
