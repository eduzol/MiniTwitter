package com.github.eduzol.minitwitter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eduzol.minitwitter.dao.IUserRepository;
import com.github.eduzol.minitwitter.domain.User;

@Service
@Transactional
public class UserService implements IUserService {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private IUserRepository userDao;
	
	@Autowired
	public UserService( IUserRepository userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public List<User> getFollowers( String username , int pageSize , int pageNumber ){
		
		List <User> followers =  userDao.getFollowers(username, pageSize, pageNumber);
		return followers;
		
	}
	
	@Override
	public List<User> getFollowees( String username , int pageSize , int pageNumber ){
		
		List <User> followers =  userDao.getFollowees(username, pageSize, pageNumber);
		return followers;
		
	}
	
}
