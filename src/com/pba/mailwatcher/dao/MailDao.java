package com.pba.mailwatcher.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.entities.Message;

@Component
public class MailDao {

	private NamedParameterJdbcTemplate jdbc;

	/**
	 * Sets the jdbc data source
	 * 
	 * @param jdbc
	 */
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/**
	 * Returns a list of messages to send
	 * 
	 * @return list of Message
	 */
	public List<Message> getMessages() {
		return jdbc.query("select * from message", new RowMapper<Message>() {
			public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setSubject(rs.getString("subject"));
				message.setRecipient(rs.getString("recipient"));
				message.setFrom(rs.getString("from"));
				message.setBody(rs.getString("body"));
				return message;
			}
		});
	}

}
