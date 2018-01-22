package com.github.eduzol.minitwitter.service;

import java.util.List;

import com.github.eduzol.minitwitter.domain.User;

public interface IUserService {

	public List<User> getFollowers( String username , int pageSize , int pageNumber );
	
}
