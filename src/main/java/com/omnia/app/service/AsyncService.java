package com.omnia.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	private static Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Async("asyncExecutor")
	public void updateCommandsWaitingTime() {
		System.out.println("updateCommandsWaitingTime");
	}

}