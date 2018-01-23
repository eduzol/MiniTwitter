package com.github.eduzol.minitwitter.service;

import java.util.List;

import com.github.eduzol.minitwitter.domain.User;

public interface IUserService {

	public List<User> getFollowers( String username , int pageSize , int pageNumber );
	public List<User> getFollowees( String username , int pageSize , int pageNumber );
	public void follow (String follower , String followee ) ;
	public void unfollow (String follower, String followee );
	public void addUser ( String handle ,String name );
	
}
