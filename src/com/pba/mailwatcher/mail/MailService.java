package com.pba.mailwatcher.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.entities.Message;

@Component
public class MailService {

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
