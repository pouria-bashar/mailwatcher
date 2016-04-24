package com.pba.mailwatcher.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.entities.Message;
import com.pba.mailwatcher.entities.MessageMapper;

@Component
public class MailWatcherDAOImp implements MailWatcherDAO {
	private SimpleJdbcCall jdbcCall;
	private NamedParameterJdbcTemplate jdbc;

	/**
	 * Sets the jdbc data source
	 * 
	 * @param jdbc
	 */
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
		this.jdbcCall = new SimpleJdbcCall(jdbc);
	}

	/**
	 * Returns a list of messages to send
	 * 
	 * @return list of Message
	 */
	public List<Message> getMessages(Integer maxNumberOfMessages) {
		jdbcCall.setProcedureName("GetMessagesToSend");
		SqlParameterSource in = new MapSqlParameterSource().addValue("MEX_NUMBER_OF_MESSAGES", maxNumberOfMessages);
		List<Message> messages = new ArrayList<>();
		Map<String, Object> result = jdbcCall.execute(in);
		for (Map.Entry<String, Object> entry : result.entrySet()) {
			
			messages.add((Message) entry.getValue());

		}
		return messages; // jdbc.query("call GetMessagesToSend(?)", params, new
						// MessageMapper());
	}

	/**
	 * Marks a mesasge as sent
	 * 
	 * @param messageID
	 *            the message ID
	 */
	public void markMessageAsSent(Integer messageID) {
		Map<String, Object> params = new HashMap<>();
		params.put("MessageID", messageID);
		jdbc.update("call MarkMessageAsSent(?)", params);
	}
}
