package com.orioltristany.oauth2backendtemplate.configuration;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

@Component
public class OAuth2RequestHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 

    public Map<String, String> getRequestParameters(OAuth2Request oAuth2Request) {
    	logger.trace("getRequestParameters");
        return oAuth2Request.getRequestParameters();
    }
}