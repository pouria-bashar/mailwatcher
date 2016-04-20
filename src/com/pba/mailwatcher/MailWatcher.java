package com.pba.mailwatcher;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.dao.MailDao;
import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.mail.MailService;

@Component
public class MailWatcher implements Runnable {
	private static final Logger logger = Logger.getLogger(MailWatcher.class.getName());
	
	@Autowired
	private MailDao mailDao;

	@Autowired
	private MailService mailService;

	@Override
	public void run() {

		List<Message> messages = mailDao.getMessages();

		if (!messages.isEmpty()) {
			/* If there are not messages Poll */
			for (Message message : messages) {
				mailService.sendMail(message);
			}
		}
		/* Sleep */
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			logger.log(Level.WARNING , "Exception trying to sleep Thread.", e);
		}

	}
}
