package com.github.eduzol.minitwitter.domain;

public class Message {

	private long id;
	private long personId;
	private String content;
	
	public Message(long id, long personId, String content) {
		super();
		this.id = id;
		this.personId = personId;
		this.content = content;
	}
	
	public Message() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", personId=" + personId + ", content=" + content + "]";
	}
}
