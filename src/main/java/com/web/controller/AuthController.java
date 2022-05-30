package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.payload.JWTAuthRequest;
import com.web.payload.JWTAuthresponse;
import com.web.security.JWTTokenHelper;


@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager manager;
	@PostMapping("/login")
	public ResponseEntity<JWTAuthresponse> createToken(
			@RequestBody JWTAuthRequest request
			){
	this.authenticate(request.getUsername(),request.getPassword());
	UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());	
	String token=this.jwtTokenHelper.generateToken(userDetails);
	JWTAuthresponse authresponse=new JWTAuthresponse();
	authresponse.setToken(token);
	
	return new ResponseEntity<JWTAuthresponse>(authresponse,HttpStatus.OK);
		
	}
	private void authenticate(String username, String password) {
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		this.manager.authenticate(usernamePasswordAuthenticationToken);
	
	
	}
}
