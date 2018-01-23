package com.github.eduzol.minitwitter.dao;

import java.util.List;

import com.github.eduzol.minitwitter.domain.User;
import com.github.eduzol.minitwitter.domain.UserIdPair;

public interface IUserRepository {
	
	public List<User> getFollowers(String username , int pageSize, int pageNumber);
	public List<User> getFollowees( String username , int pageSize, int pageNumber);
	public void follow (String follower , String followee ) ;
	public void unfollow (String follower, String followee );
	public void addUser ( String handle ,String name );
	public List<UserIdPair> getUsersAndMostPopularFollower();
	
}
