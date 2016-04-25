package com.pba.mailwatcher.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.pba.mailwatcher.entities.Message;
public class MailWatcherDAOImpTest {

	private boolean setDataSourceCalled;
	
	@Before
	public void setup(){
		setDataSourceCalled = false;
	}
	
	@Test
	public void test_getMessages(){
		
		MailWatcherDAO mailWatcherDAO = new MailWatcherDAOImp(){
			@Override
			public JdbcTemplate getJdbcTemplate() {
				return new JdbcTemplate(){
					@Override
					public <T> List<T> query(PreparedStatementCreator psc, RowMapper<T> rowMapper)
							throws DataAccessException {
						// TODO Auto-generated method stub
						return null;
					}
				};
				
			}
			@Override
			public void setDataSource(DataSource dataSource) {	
				setDataSourceCalled = true;
			}
		};
		assertTrue(setDataSourceCalled);
		
		List<Message> messages = mailWatcherDAO.getMessages(10);
		
		assertEquals(0, messages.size());
	}
}
