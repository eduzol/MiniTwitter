package com.github.eduzol.minitwitter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.eduzol.minitwitter.domain.Message;

@Repository
public class MessageJdbcRepository implements IMessageRepository{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MessageJdbcRepository( JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Message findById(long id) {
		
		Message message =  jdbcTemplate.queryForObject("select * from messages where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Message>(Message.class));
		
		System.out.println("Message found " + message);
		
		return message;
	}
}
