package com.pba.mailwatcher.entities;


public class Message {

	private Integer id;
	private String subject;
	private String from;
	private String recipient;
	private String body;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

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
