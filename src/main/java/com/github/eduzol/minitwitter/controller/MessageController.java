package com.github.eduzol.minitwitter.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.eduzol.minitwitter.domain.Message;
import com.github.eduzol.minitwitter.service.IAuthenticationService;
import com.github.eduzol.minitwitter.service.IMessageService;

@RestController
public class MessageController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private IMessageService messageService;
	private IAuthenticationService authService;
	
	@Autowired
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
    @Autowired
	public void setAuthService(IAuthenticationService authService) {
		this.authService = authService;
	}

	@RequestMapping(path ="/message/{messageId}" , method=RequestMethod.GET)
	public ResponseEntity<Message> getMessagesById(  @PathVariable Long messageId   ) {
		
		Message message =  messageService.getMessageById(messageId);
		String username = authService.getAuthentication().getName();
		logger.info(username+" " + message.toString());
		return  ResponseEntity.ok(message);
		
	}
	
	@RequestMapping(path="/messages" , method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessagesByUser(  @RequestParam(name="page-size") Integer pageSize , 
			@RequestParam(name="page") Integer pageNumber  , @RequestParam(required=false , name="search") String searchTerm ) {
		
		String username = authService.getAuthentication().getName();
		logger.info(username);
		List<Message> messages = messageService.getMessagesByUser(username, pageSize, pageNumber, Optional.ofNullable(searchTerm) );
		return  ResponseEntity.ok(messages);
		
	}
	
}
