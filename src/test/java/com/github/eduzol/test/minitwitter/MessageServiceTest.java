package com.github.eduzol.test.minitwitter;

import java.util.List;
import java.util.Optional;

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
		
		long id = 1;
		Message message = messageService.getMessageById(id);
		logger.info("Test  " + message);
		Assert.assertNotNull(message);
		Assert.assertTrue(message.getId() == id);
	}
	
	@Test
	public void testMessageServiceGetMessagesNoSearch() {
		
		String username = "batman";
		long knownId = 1;
		int pageSize = 20;
		int page = 1;
		
		List<Message> messages = messageService.getMessagesByUser(username, pageSize, page, Optional.empty());

		//Number of messages retrieved must be less than specified page Size
		Assert.assertTrue(messages.size() <= pageSize);
		messages.forEach( message -> {
			Assert.assertTrue(message.getPersonId() == knownId);
		});
		
	}
	
	@Test
	public void testMessageServiceGetMessagesSearch() {
		
		String username = "batman";
		long knownId = 1;
		int pageSize = 20;
		int page = 1;
		String searchTerm = "curabitur massa";
		List<Message> messages = messageService.getMessagesByUser(username, pageSize, page,  Optional.ofNullable(searchTerm));
		
		//Number of messages retrieved must be less than specified page Size
		Assert.assertTrue(messages.size() <= pageSize);
		messages.forEach( message -> {
			//message must belong to user
			Assert.assertTrue(message.getPersonId() == knownId);
			//message must contains searchTerm
			Assert.assertTrue(message.getContent().matches("(?i:.*"+searchTerm+".*)"));
		});
	} 
}
