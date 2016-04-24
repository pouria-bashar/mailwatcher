package com.pba.mailwatcher.dao;

import java.util.List;

import com.pba.mailwatcher.entities.Message;

/**
 * MailWatcher database access interface
 * @author Pouria
 */
public interface MailWatcherDAO {

	/**
	 * Return a list of messages to process
	 * @param maxNumberOfMessages maximum number of messages to process
	 * @return List of messages
	 */
	public List<Message> getMessages(Integer maxNumberOfMessages);
	
	/**
	 * Marks message as sent
	 * @param messageID the message id to mark
	 */
	public void markMessageAsSent(Integer messageID);
	
	
	
}
