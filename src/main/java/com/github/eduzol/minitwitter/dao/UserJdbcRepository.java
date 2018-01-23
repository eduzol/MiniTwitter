package com.github.eduzol.minitwitter.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.eduzol.minitwitter.domain.User;
import com.github.eduzol.minitwitter.domain.UserIdPair;
import com.github.eduzol.minitwitter.util.UserIdPairRowMapper;
import com.github.eduzol.minitwitter.util.UserRowMapper;

@Repository
@Transactional
public class UserJdbcRepository implements IUserRepository {
	
	
	private JdbcTemplate jdbcTemplate;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UserJdbcRepository( JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> getFollowers(String username , int pageSize, int pageNumber) {
		
		String sql = "SELECT T.* FROM FOLLOWERS F INNER JOIN PEOPLE P ON F.PERSON_ID = P.ID \r\n" + 
				"INNER JOIN PEOPLE T ON T.ID=F.FOLLOWER_PERSON_ID \r\n" + 
				"WHERE P.HANDLE= ? \r\n" + 
				"ORDER BY F.FOLLOWER_PERSON_ID  LIMIT ? OFFSET ?";	
		
		int offset = pageSize*(pageNumber-1);
		List<User> followers =  jdbcTemplate.query(sql, new Object[] { username, pageSize, offset }, new UserRowMapper()); 
		return followers;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> getFollowees( String username , int pageSize, int pageNumber ) {
		
		String sql =  "SELECT P.* FROM FOLLOWERS F INNER JOIN PEOPLE P on F.PERSON_ID = P.ID\r\n" + 
				"INNER JOIN PEOPLE T ON F.FOLLOWER_PERSON_ID = T.ID\r\n" + 
				" WHERE T.HANDLE = ? ORDER BY F.ID LIMIT ? OFFSET ?" ;
		
		int offset = pageSize*(pageNumber-1);
		List<User> followees =  jdbcTemplate.query(sql, new Object[] { username, pageSize, offset }, new UserRowMapper()); 
		return followees;
	} 
	
	
	//userService.follow( 'batman' , 'superman'); //Batman follows superman
	@Override
	public void follow (String follower , String followee ) {
		
		String sql = "INSERT INTO followers(person_id, follower_person_id)\r\n" + 
				"VALUES\r\n" + 
				"((SELECT ID from PEOPLE WHERE HANDLE=?), (SELECT ID from PEOPLE WHERE HANDLE=?));";
		
		jdbcTemplate.update(sql, followee, follower);
		
	}
	
	//userService.unfollow( 'batman' , 'superman') //batman unfollows superman
	@Override
	public void unfollow (String follower, String followee ) {
		
		String sql = "DELETE FROM FOLLOWERS \r\n" + 
				"WHERE PERSON_ID = (SELECT ID FROM PEOPLE WHERE HANDLE= ?) \r\n" + 
				"AND FOLLOWER_PERSON_ID = (SELECT ID FROM PEOPLE WHERE HANDLE= ?)";
		
		jdbcTemplate.update(sql,  followee, follower);
		
	} 
	
	@Override
	public void addUser ( String handle ,String name ) {
		
		String sql = "INSERT INTO PEOPLE(HANDLE, NAME) VALUES (?, ?);";
		jdbcTemplate.update(sql, handle, name);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UserIdPair> getUsersAndMostPopularFollower(){
		
		//TODO add pagination to sql query and Service to protect from overflows
		/**
		 * Joining with simple group-identifier, max-value-in-group Sub-query
		 * https://stackoverflow.com/questions/7745609/sql-select-only-rows-with-max-value-on-a-column
		 */
		String sql = "SELECT A.* from ( SELECT t1.person_id, t1.follower_person_id, t2.cnt FROM followers AS t1 JOIN (    SELECT person_id, COUNT(*) AS cnt FROM followers GROUP BY person_id ) AS t2  ON t1.follower_person_id = t2.person_id ORDER BY t1.person_id , CNT DESC ) as A inner join (SELECT t1.person_id,   MAX( t2.cnt ) AS MAX_NUM_FOLLOWERS FROM followers AS t1 JOIN ( SELECT person_id, COUNT(*) AS cnt FROM followers GROUP BY person_id ) AS t2  ON t1.follower_person_id = t2.person_id GROUP BY (T1.PERSON_ID) ORDER BY t1.person_id ) as B on A.person_id = b.person_id AND A.cnt = B.MAX_NUM_FOLLOWERS;";
		List<UserIdPair> result = jdbcTemplate.query(sql, new UserIdPairRowMapper());
		if (logger.isDebugEnabled()) {
			result.forEach( pair -> logger.debug("pair found " + pair));
		}
		logger.info("found " + result.size() + " pairs");
		return result;
	}
}
