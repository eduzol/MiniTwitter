package com.github.eduzol.minitwitter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.eduzol.minitwitter.domain.Message;

@Repository
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
		// TODO Auto-generated method stub
		String sql = "select MESSAGES.*  from PEOPLE INNER JOIN MESSAGES ON PEOPLE.ID = MESSAGES.PERSON_ID WHERE HANDLE=? order by ID LIMIT ? OFFSET ?";
		int offset = pageSize*(pageNumber-1);
		List<Message> messages =  jdbcTemplate.query(sql ,new Object[]{username, pageSize,offset}, new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setContent(rs.getString("CONTENT"));
                message.setId(Long.parseLong(rs.getString("ID")));
                message.setPersonId(Long.parseLong(rs.getString("PERSON_ID")));
                return message;
            }
        }); 
		return messages;
		
	}

	@Override
	public List<Message> getMessagesByUser(String username,int pageSize, int pageNumber,  String searchterm ) {
		// TODO Auto-generated method stub
		return null;
	}
}
