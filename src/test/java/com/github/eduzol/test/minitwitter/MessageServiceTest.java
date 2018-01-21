package com.github.eduzol.test.minitwitter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.eduzol.minitwitter.WebApplication;
import com.github.eduzol.minitwitter.domain.Message;
import com.github.eduzol.minitwitter.service.IMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebApplication.class , webEnvironment=WebEnvironment.RANDOM_PORT)
public class MessageServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IMessageService messageService;
	
	@Test
	public void testMessageService() {
		
		Message message = messageService.getMessageById(1);
		logger.info("Test  " + message);
		Assert.assertNotNull(message);
	
	}
		
	
}
