package com.github.eduzol.minitwitter.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.eduzol.minitwitter.domain.UserIdPair;

public class UserIdPairRowMapper implements RowMapper<UserIdPair> {
	
	@Override
	public UserIdPair mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UserIdPair pair = new UserIdPair();
    		pair.setPersonId(rs.getLong("PERSON_ID"));
    		pair.setFollowerPersonId(rs.getLong("FOLLOWER_PERSON_ID"));
        	pair.setFollowerCount(rs.getInt("CNT"));
        return pair;
    }

}
