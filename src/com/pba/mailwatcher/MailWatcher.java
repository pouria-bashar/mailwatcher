package com.pba.mailwatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pba.mailwatcher.dao.MailDao;
import com.pba.mailwatcher.entities.Message;
@Component
public class MailWatcher {

	@Autowired
	private MailDao mailDao;
	
	
	public void test(){
		List<Message> messages = mailDao.getMessages();
		for(Message m : messages){
			System.out.println(m);
		}
	}
}
