package com.pba.mailwatcher;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.dao.MailWatcherDAO;
import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.mail.MailService;

@Component
public class MailWatcher implements Runnable {
	private static final Logger logger = Logger.getLogger(MailWatcher.class.getName());
	
	@Autowired
	private MailWatcherDAO mailWatcherDAO;

	@Autowired
	private MailService mailService;

	@Override
	public void run() {

		List<Message> messages = mailWatcherDAO.getMessages();

		if (!messages.isEmpty()) {
			for (Message message : messages) {
				mailService.sendMail(message);
				/* Mark message as sent */
				
			}
		}
		/* If there are not messages Poll */
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			logger.log(Level.WARNING , "Exception trying to sleep Thread.", e);
		}

	}
}
