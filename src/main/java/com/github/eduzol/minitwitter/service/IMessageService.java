package com.github.eduzol.minitwitter.service;

import java.util.List;
import java.util.Optional;

import com.github.eduzol.minitwitter.domain.Message;

public interface IMessageService {

	public Message getMessageById( long id);
	public List<Message> getMessagesByUser(String username , int pageSize,  int pageNUmber , Optional<String> searchTerm);
	
}
