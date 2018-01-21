package com.github.eduzol.minitwitter.service;

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

}
