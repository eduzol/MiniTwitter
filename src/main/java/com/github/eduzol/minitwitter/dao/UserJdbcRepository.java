package com.github.eduzol.minitwitter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.eduzol.minitwitter.domain.User;

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
		List<User> followers =  jdbcTemplate.query(sql, new Object[] { username, pageSize, offset }, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setHandle(rs.getNString("HANDLE"));
                user.setId(Long.parseLong(rs.getNString("ID")));
                user.setName(rs.getNString("NAME"));
                return user;
            }
        }); 
		
		return followers;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> getFollowees( String username , int pageSize, int pageNumber ) {
		
		String sql =  "SELECT P.* FROM FOLLOWERS F INNER JOIN PEOPLE P on F.PERSON_ID = P.ID\r\n" + 
				"INNER JOIN PEOPLE T ON F.FOLLOWER_PERSON_ID = T.ID\r\n" + 
				" WHERE T.HANDLE = ? ORDER BY F.ID LIMIT ? OFFSET ?" ;
		
		int offset = pageSize*(pageNumber-1);
		List<User> followees =  jdbcTemplate.query(sql, new Object[] { username, pageSize, offset }, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setHandle(rs.getNString("HANDLE"));
                user.setId(Long.parseLong(rs.getNString("ID")));
                user.setName(rs.getNString("NAME"));
                return user;
            }
        }); 
		
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
}
