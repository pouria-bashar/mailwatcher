package com.pba.mailwatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.dao.MailDao;
import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.mail.MailService;

@Component
public class MailWatcher implements Runnable {

	@Autowired
	private MailDao mailDao;

	@Autowired
	private MailService mailService;

	@Override
	public void run() {

		List<Message> messages = mailDao.getMessages();
		/* If there are not messages Poll */
		if(messages.size() < 1){
			
		}
		for (Message message : messages) {
			mailService.sendMail(message);
		}
	}
}
