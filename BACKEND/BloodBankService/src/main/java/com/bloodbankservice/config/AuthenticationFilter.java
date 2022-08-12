package com.bloodbankservice.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bloodbankservice.model.LoginModel;
import com.bloodbankservice.services.BloodBankService;
import com.bloodbankservice.shared.BloodBankDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private BloodBankService userService;
	//private Environment environment;
	
	//constructor to initialize userService & environment
	public AuthenticationFilter(BloodBankService userService,AuthenticationManager authenticationManager) {
		this.userService=userService;
	//	this.environment=environment;
		super.setAuthenticationManager(authenticationManager);
	}
	//The method attemptAuthentication will receive user's credentials from client side and then attempts to authenticate it
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException{
		try {
			//fetching the credentials into LoginModel
			LoginModel creds=new ObjectMapper().readValue(req.getInputStream(),LoginModel.class);
			//calling the authenticate method by passing and email as userId , password & an empty ArrayList of authorities
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),new ArrayList<>()));
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	/*This method will get automatically invoked by the spring security framework if authentication is successful
	 * The main responsibility of this method is to fetch the user details and generate the jwt token and then add
	 * that jwt token to the response header and send it back with http
	*/
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth) throws IOException,ServletException{
		
		String userName=((User) auth.getPrincipal()).getUsername();
		BloodBankDto userDetails=userService.getUserDetailsByEmail(userName);
		
		String token=Jwts.builder()
				.setSubject(userDetails.getUserId())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512,SecurityConstants.TOKEN_SECRET)
				.compact();
		
		
//		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
//		res.addHeader("userId", userDetails.getUserId());
		String obj= token+" "+userDetails.getUserId();
		res.getWriter().write(obj);
		res.getWriter().flush();
	}
}
