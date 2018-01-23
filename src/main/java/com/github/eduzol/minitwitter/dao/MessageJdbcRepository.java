package com.github.eduzol.minitwitter.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.eduzol.minitwitter.domain.Message;
import com.github.eduzol.minitwitter.util.MessageMapper;

@Repository
@Transactional
public class MessageJdbcRepository implements IMessageRepository{

	private JdbcTemplate jdbcTemplate;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public MessageJdbcRepository( JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Message findById(long id) {
		
		Message message =  jdbcTemplate.queryForObject("select * from messages where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Message>(Message.class));
		
		logger.info("Message found " + message);
		return message;
	}

	@Override
	public List<Message> getMessagesByUser(String username, int pageSize, int pageNumber) {
		
		String sql = "select MESSAGES.*  from PEOPLE INNER JOIN MESSAGES ON PEOPLE.ID = MESSAGES.PERSON_ID WHERE HANDLE=? order by ID LIMIT ? OFFSET ?";
		int offset = pageSize*(pageNumber-1);
		List<Message> messages =  jdbcTemplate.query(sql ,new Object[]{username, pageSize,offset}, new MessageMapper()); 
		return messages;
		
	}

	@Override
	public List<Message> getMessagesByUser(String username,int pageSize, int pageNumber,  String searchTerm ) {
		
		String sql = "select MESSAGES.*  from PEOPLE INNER  JOIN MESSAGES ON PEOPLE.ID = MESSAGES.PERSON_ID \r\n" + 
				"JOIN FT_SEARCH_DATA(?,0, 0) FT\r\n" + 
				"WHERE HANDLE=? AND FT.TABLE = 'MESSAGES' AND MESSAGES.ID=FT.KEYS[0] order by ID LIMIT ? OFFSET ?;";
		int offset = pageSize*(pageNumber-1);
		List<Message> messages =  jdbcTemplate.query(sql ,new Object[]{searchTerm, username, pageSize,offset}, new MessageMapper() ); 
		return messages;
	}
}
