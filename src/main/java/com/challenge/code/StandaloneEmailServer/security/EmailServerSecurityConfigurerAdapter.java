package com.challenge.code.StandaloneEmailServer.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration adapter implementation that governs the authentication method.
 * 
 * Also defines which Rest requests to be authenticated.
 * 
 * @author aelsayed
 */
@Configuration
@EnableWebSecurity
public class EmailServerSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// allow in memory authentication for mock user
		auth.inMemoryAuthentication().withUser("mockUser").password("mockPassword").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authorize access to POST requests
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/SendEmail").hasRole("USER");

		// disable CSRF protection.
		// note: Of course it is recommended to protect against Cross Site Request
		// Forgery but I am just show casing user authorization only because of time
		// limit.
		// Room for improvement is to implement a random token validation mechanism to
		// verify incoming requests.
		http.csrf().disable();
	}

}
