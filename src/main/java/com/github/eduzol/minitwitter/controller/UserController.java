package com.github.eduzol.minitwitter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.eduzol.minitwitter.domain.User;
import com.github.eduzol.minitwitter.service.AuthenticationService;
import com.github.eduzol.minitwitter.service.IAuthenticationService;
import com.github.eduzol.minitwitter.service.IUserService;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IUserService userService;
	private IAuthenticationService authService;
	
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setAuthService(IAuthenticationService authService) {
		this.authService = authService;
	}
	
	@RequestMapping(path="/followers" , method = RequestMethod.GET)
	public ResponseEntity<List<User>> getFollowersByUser(  @RequestParam(name="page-size") Integer pageSize , 
			@RequestParam(name="page") Integer pageNumber  ) {
		
		String username = authService.getAuthentication().getName();
		List<User> followers = userService.getFollowers(username, pageSize, pageNumber);
		return  ResponseEntity.ok(followers);

	}
	
	@RequestMapping(path="/followees" , method = RequestMethod.GET)
	public ResponseEntity<List<User>> getFolloweesByUser(  @RequestParam(name="page-size") Integer pageSize , 
			@RequestParam(name="page") Integer pageNumber  ) {
		
		String username = authService.getAuthentication().getName();
		List<User> followers = userService.getFollowers(username, pageSize, pageNumber);
		return  ResponseEntity.ok(followers);
	}
	
}

