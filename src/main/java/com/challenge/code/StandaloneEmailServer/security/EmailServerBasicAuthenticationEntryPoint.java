package com.challenge.code.StandaloneEmailServer.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Entry point used to intercept AuthenticationException.
 * 
 * Also sets the response message.
 * 
 * @author aelsayed
 *
 */
@Component
public class EmailServerBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

	@Override
	public void afterPropertiesSet() throws Exception {
		// realmName must be set.
		setRealmName("EmailServerRealm");
		super.afterPropertiesSet();
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// write custom message in case of authentication exception
		PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authException.getMessage());
	}

}
