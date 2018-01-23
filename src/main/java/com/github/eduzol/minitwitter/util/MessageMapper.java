package com.github.eduzol.minitwitter.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.eduzol.minitwitter.domain.Message;

public class MessageMapper implements RowMapper<Message> {
	
	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setContent(rs.getString("CONTENT"));
        message.setId(Long.parseLong(rs.getString("ID")));
        message.setPersonId(Long.parseLong(rs.getString("PERSON_ID")));
        return message;
    }
}
