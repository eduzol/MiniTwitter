package com.github.eduzol.test.minitwitter;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.eduzol.minitwitter.WebApplication;
import com.github.eduzol.minitwitter.domain.User;
import com.github.eduzol.minitwitter.domain.UserIdPair;
import com.github.eduzol.minitwitter.service.IUserService;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebApplication.class , webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testMessageService() {
		
		String username =  "batman";
		int pageSize = 10;
		int pageNumber = 1;
		List<User> followers = userService.getFollowers(username, pageSize, pageNumber);
		followers.forEach(follower -> logger.info(follower.toString()));
		Assert.assertTrue(followers.size() <= pageSize);
	}
	
	@Test 
	public void testUserPairsByMostPopularFollower () {
		
		List<UserIdPair> pairs = userService.getUsersAndMostPopularFollower();
		//TODO implement API to retrieve user and follower then verify pair follower/followee relationship and follower count
		pairs.forEach( pair -> logger.info(pair.toString()));
	}

}
