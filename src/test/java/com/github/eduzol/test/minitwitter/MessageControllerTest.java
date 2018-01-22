package com.github.eduzol.test.minitwitter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.eduzol.minitwitter.WebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebApplication.class , webEnvironment=WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {
	
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	
	@Before
	public void setUp(){
		mvc  = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSampleMessageSearch() throws Exception {
		
		String username = "batman";
		int knownUserId =1;
		String password = "user1Pass";
		int pageSize  = 20;
		int pageNumber = 1;
		String searchTerm = "eu";
		
		mvc
			.perform(get("/messages")
					.param("page", pageNumber+"")
		            .param("page-size", pageSize+"") 
		            .param("search", searchTerm)
					.with( httpBasic(username,password)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[*].personId",hasItem(knownUserId) ))
			.andExpect(jsonPath("$[*].content",  hasItem(containsString(searchTerm)) ))
			.andDo(print());
		
	}

}
