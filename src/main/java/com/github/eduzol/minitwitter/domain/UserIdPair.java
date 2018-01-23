package com.github.eduzol.minitwitter.domain;

public class UserIdPair {
	
	private Long personId;
	private Long followerPersonId;
	private int followerCount;
	
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getFollowerPersonId() {
		return followerPersonId;
	}
	public void setFollowerPersonId(Long followerPersonId) {
		this.followerPersonId = followerPersonId;
	}
	public int getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}
	
	@Override
	public String toString() {
		return "UserIdPair [personId=" + personId + ", followerPersonId=" + followerPersonId + ", followerCount="
				+ followerCount + "]";
	}
	
	
	
	
}
