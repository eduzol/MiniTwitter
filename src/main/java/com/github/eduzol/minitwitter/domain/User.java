package com.github.eduzol.minitwitter.domain;

public class User {
	
	private Long id;
	private String handle;
	private String name;
	
	public User() {
		super();
	}
	public User(Long id, String handle, String name) {
		super();
		this.id = id;
		this.handle = handle;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", handle=" + handle + ", name=" + name + "]";
	}
	
}
