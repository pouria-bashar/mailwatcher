package com.pba.mailwatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.pba.mailwatcher.entities.Message;

public class MailWatcherService {

	@Autowired
	private MailSender mailSender;

	public void sendMail(Message message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom(message.getFrom());
		simpleMailMessage.setTo(message.getRecipient());
		simpleMailMessage.setSubject(message.getSubject());
		simpleMailMessage.setText(message.getBody());
		mailSender.send(simpleMailMessage);
	}
}
