package com.pba.mailwatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailwatcherConfig {

	@Value("${mailwatcher.poll.time}")
	private Long maxPollTime;
	
	@Value("${mailwatcher.max.messages}")
	private Integer maxNumberOfMessages;
	
	/**
	 * @return the max number of messages to process 
	 */
	public Integer getMaxNumberOfMessages() {
		return maxNumberOfMessages;
	}
	
	/**
	 * @return the max amount to poll if there are no messages to process
	 */
	public Long getMaxPollTime() {
		return maxPollTime;
	}
}
