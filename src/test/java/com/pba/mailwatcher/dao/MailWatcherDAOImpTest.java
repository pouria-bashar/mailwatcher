package com.pba.mailwatcher.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pba.mailwatcher.entities.Message;

public class MailWatcherDAOImpTest {

	@Test
	public void test_getMessages() {
		final int maxNumberOfMessages = 10;
		MailWatcherDAO mailWatcherDAO = new MailWatcherDAOImp() {
			@Override
			public JdbcTemplate getJdbcTemplate() {
				return new JdbcTemplate() {
					@Override
					public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper)
							throws DataAccessException {
						assertEquals(1, args.length);
						assertEquals(maxNumberOfMessages, args[0]);
						List messages = new ArrayList<>();
						messages.add(new Message());
						
						return messages;
					}
				};

			}
		};

		List<Message> messages = mailWatcherDAO.getMessages(maxNumberOfMessages);
		assertNotNull(messages);
	}
}
