package com.bloodbankservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bloodbankservice.services.BloodBankService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private BloodBankService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	// private Environment environment;

	@Autowired
	public WebSecurity(Environment environment, BloodBankService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		 //this.environment=environment;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/bloodbank/register").permitAll().anyRequest().authenticated().and()
				.addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/bloodbank/login");
		authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
}
