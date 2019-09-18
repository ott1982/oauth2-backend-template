package com.orioltristany.oauth2backendtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class Oauth2BackendTemplateApplication {

	public static void main(String... args) {
		SpringApplication.run(Oauth2BackendTemplateApplication.class, args);
	}
}