package com.pba.mailwatcher.entities;


public class Message {

	private Integer id;
	
	private String subject;
	
	public Integer getId() {
		return id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + "]";
	}
	
	
}
