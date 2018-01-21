package com.github.eduzol.minitwitter.dao;

import java.util.List;

import com.github.eduzol.minitwitter.domain.Message;

public interface IMessageRepository {

	
	public Message findById(long id);
	public List<Message> getMessagesByUser(String username , int pageSize,  int pageNUmber);
	public List<Message> getMessagesByUser(String username , int pageSize,  int pageNUmber, String searchterm);
	
}
