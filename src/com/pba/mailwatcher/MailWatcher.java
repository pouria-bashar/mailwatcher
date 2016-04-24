package com.pba.mailwatcher;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.dao.MailWatcherDAO;
import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.mail.MailService;

/**
 * Checks database for messages to send and sends them via SMTP
 * 
 * @author Pouria
 */
@Component
public class MailWatcher implements Runnable {
	private static final Logger logger = Logger.getLogger(MailWatcher.class.getName());

	/* Config object */
	@Autowired
	private MailwatcherConfig config;

	/* Milawatcher database access */
	@Autowired
	private MailWatcherDAO mailWatcherDAO;
	/* Mail service */
	@Autowired
	private MailService mailService;

	@Override
	public void run() {

		while (true) {
			/* get a list of messages to send */
			List<Message> messages = mailWatcherDAO.getMessages(config.getMaxNumberOfMessages());
			logger.log(Level.INFO, "Retrieved {0} messages", messages.size());

			/* If there are messages to be processed */
			if (!messages.isEmpty()) {
				for (Message message : messages) {
					mailService.sendMail(message);
					/* Mark message as sent */
					mailWatcherDAO.markMessageAsSent(message.getId());
					logger.log(Level.INFO, "Successfully processed message {0}", message.getId());
				}
			}
			/* If there are not messages Poll */
			try {
				 
				logger.log(Level.INFO, "Sleeping for {0}", config.getMaxPollTime());
				TimeUnit.SECONDS.sleep(config.getMaxPollTime());
			} catch (InterruptedException e) {
				logger.log(Level.WARNING, "Exception trying to sleep Thread.", e);
			}
		}

	}
}
