package com.github.eduzol.minitwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.eduzol.minitwitter.domain.Message;
import com.github.eduzol.minitwitter.service.IMessageService;

@RestController
public class MessageController {
	
	private IMessageService messageService;
	
	@Autowired
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(path ="/message/{messageId}" , method=RequestMethod.GET)
	public String getMessagesByUser( @PathVariable Long messageId   ) {
		
		Message message =  messageService.getMessageById(messageId);
		System.out.println(message);
		return "Endpoint OK - " + message;
	}
	
}
