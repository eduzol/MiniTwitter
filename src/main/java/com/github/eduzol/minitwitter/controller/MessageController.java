package com.github.eduzol.minitwitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.eduzol.minitwitter.domain.Message;
import com.github.eduzol.minitwitter.service.IMessageService;

@RestController
public class MessageController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private IMessageService messageService;
	
	@Autowired
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(path ="/message/{messageId}" , method=RequestMethod.GET)
	public String getMessagesByUser( @PathVariable Long messageId   ) {
		
		Message message =  messageService.getMessageById(messageId);
		logger.info("***************"+message.toString());
		return "Endpoint OK - " + message;
	}
	
}
