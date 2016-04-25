package com.pba.mailwatcher.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MessageMapper implements RowMapper<Message> {
	   public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setSubject(rs.getString("subject"));
			message.setRecipient(rs.getString("recipient"));
			message.setFrom(rs.getString("from"));
			message.setBody(rs.getString("body"));
			return message;
		   }
		}