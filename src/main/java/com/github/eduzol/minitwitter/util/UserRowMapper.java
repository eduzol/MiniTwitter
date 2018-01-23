package com.github.eduzol.minitwitter.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.eduzol.minitwitter.domain.User;

public class UserRowMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setHandle(rs.getNString("HANDLE"));
        user.setId(Long.parseLong(rs.getNString("ID")));
        user.setName(rs.getNString("NAME"));
        return user;
    }
}
