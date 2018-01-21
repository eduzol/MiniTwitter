package com.github.eduzol.minitwitter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eduzol.minitwitter.dao.IMessageRepository;
import com.github.eduzol.minitwitter.domain.Message;

@Service
public class MessageService implements IMessageService {

	private IMessageRepository messageDao;
	
	
	public IMessageRepository getMessageDao() {
		return messageDao;
	}

	@Autowired
	public void setMessageDao(IMessageRepository messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	@Transactional
	public Message getMessageById(long id) {
		
		Message message = messageDao.findById(id);
		return message;
	}

	@Override
	public List<Message> getMessagesByUser(String username,  int pageSize, int pageNumber , Optional<String> searchTerm) {

		if (! searchTerm.isPresent()) {
			return	messageDao.getMessagesByUser(username, pageSize, pageNumber);
		}else {
			return messageDao.getMessagesByUser(username,  pageSize, pageNumber , searchTerm.get());
		}
		
	}

}
