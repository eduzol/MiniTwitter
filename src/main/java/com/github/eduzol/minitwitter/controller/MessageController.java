package com.github.eduzol.minitwitter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	
	@RequestMapping(path ="/messages" , method=RequestMethod.GET)
	public String getMessagesByUser() {
		System.out.println("Endpoint");
		return "Endpoint OK";
	}
	
}
