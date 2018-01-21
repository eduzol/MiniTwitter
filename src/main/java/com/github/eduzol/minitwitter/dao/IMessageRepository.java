package com.github.eduzol.minitwitter.dao;

import com.github.eduzol.minitwitter.domain.Message;

public interface IMessageRepository {

	public Message findById(long id);
	
}
