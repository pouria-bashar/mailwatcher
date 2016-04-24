package com.pba.mailwatcher;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.dao.MailWatcherDAO;
import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.mail.MailService;

@Component
public class MailWatcher implements Runnable {
	private static final Logger logger = Logger.getLogger(MailWatcher.class.getName());
	
	@Autowired
	private MailwatcherConfig config;

	@Autowired
	private MailWatcherDAO mailWatcherDAO;

	@Autowired
	private MailService mailService;

	@Override
	public void run() {

		List<Message> messages = mailWatcherDAO.getMessages(config.getMaxNumberOfMessages());
		logger.log(Level.FINE, "Retrieved {0} messages", messages.size());

		if (!messages.isEmpty()) {
			for (Message message : messages) {
				mailService.sendMail(message);
				/* Mark message as sent */
				mailWatcherDAO.markMessageAsSent(message.getId());
				logger.log(Level.FINE, "Successfully processed message {0}", message.getId());
			}
		}
		/* If there are not messages Poll */
		try {
			
			
			Thread.sleep(config.getMaxPollTime());
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, "Exception trying to sleep Thread.", e);
		}

	}
}
