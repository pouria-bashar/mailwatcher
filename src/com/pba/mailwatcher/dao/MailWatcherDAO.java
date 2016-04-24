package com.pba.mailwatcher.dao;

import java.util.List;

import com.pba.mailwatcher.entities.Message;

/**
 * MailWatcher database access interface
 * @author Pouria
 */
public interface MailWatcherDAO {

	public List<Message> getMessages(Integer maxNumberOfMessages);
	
	public void markMessageAsSent(Integer messageID);
	
	
	
}
