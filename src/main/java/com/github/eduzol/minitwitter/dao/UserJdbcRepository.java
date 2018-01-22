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
}
