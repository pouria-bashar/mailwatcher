package com.pba.mailwatcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/pba/mailwatcher/beans/applicationContext.xml");

		MailWatcher mailWatcher = (MailWatcher) context.getBean("mailWatcher");

		mailWatcher.run();

		((ClassPathXmlApplicationContext) context).close();
	}
}
