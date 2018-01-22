package com.github.eduzol.minitwitter.dao;

import java.util.List;

import com.github.eduzol.minitwitter.domain.User;

public interface IUserRepository {
	
	public List<User> getFollowers(String username , int pageSize, int pageNumber);
	public List<User> getFollowees( String username , int pageSize, int pageNumber);
	
}
