package com.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.challenge.login.LoggedUser;
import com.challenge.repository.UserRepository;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth, UserRepository repository) throws Exception {
		try {	
			auth.userDetailsService(email -> repository.findByEmail(email).map(LoggedUser::new).orElse(null));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.GET,
				"/", //
				"/webjars/**", //
				"/*.html", //
				"/favicon.ico", //
				"/**/*.html", //
				"/v2/api-docs", //
				"/configuration/ui", //
				"/swagger-resources/**", //
				"/configuration/**", //
				"/swagger-ui.html", //
				"/webjars/**", //
				"/**/*.css", //
				"/**/*.js"// 
			);
	}
}
