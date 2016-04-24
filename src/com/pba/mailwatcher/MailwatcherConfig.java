package com.pba.mailwatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailwatcherConfig {

	@Value("${mailwatcher.poll.time}")
	private Long maxPollTime;
	@Value("${mailwatcher.max.messages}")
	private Integer maxNumberOfMessages;
	
	public Integer getMaxNumberOfMessages() {
		return maxNumberOfMessages;
	}
	
	public Long getMaxPollTime() {
		return maxPollTime;
	}
}
