package com.pba.mailwatcher.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.entities.MessageMapper;

@Component
public class MailWatcherDAOImp implements MailWatcherDAO {
	private JdbcTemplate jdbcTemplate;
	/**
	 * Sets the jdbc data source
	 * @param dataSource the  data source
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Returns a list of messages to send
	 * 
	 * @return list of Message
	 */
	public List<Message> getMessages(Integer maxNumberOfMessages) {
		return getJdbcTemplate().query( "call GetMessagesToSend(?)", new Object[] { maxNumberOfMessages }, new MessageMapper());		
	}

	/**
	 * Marks a mesasge as sent
	 * @param messageID the message ID
	 */
	public void markMessageAsSent(Integer messageID) {
		getJdbcTemplate().update("call MarkMessageAsSent(?)",new Object[] { messageID });
	}
	
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
