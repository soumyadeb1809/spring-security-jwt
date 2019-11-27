package com.soumya.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soumya.spring_security.config.MyUserDetailsService;
import com.soumya.spring_security.model.AuthenticationRequest;
import com.soumya.spring_security.model.AuthenticationResponse;
import com.soumya.spring_security.util.JwtUtil;

@RestController
public class HomeController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private  JwtUtil jwtUtil;
	
	@GetMapping("/hello")
	public String index() {
		return "<h1>Home Page</h1>";
	}
	
	
	@PostMapping("/authenticate")
	public @ResponseBody ResponseEntity<?> autheticate(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
			);
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect user details");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
		
		String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
