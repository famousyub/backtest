package com.omnia.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.omnia.app.service.CommandeService;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	// private final AsyncService service;

	@Autowired
	private CommandeService commandeService;

	/*
	 * public ScheduledTasks(AsyncService service) { this.service = service; }
	 */

	@Scheduled(fixedRate = 180_000)
	public void reportCurrentTime() {
		// this.service.updateCommandsWaitingTime();

		commandeService.updateCommandesWaitingTime();

	}
	
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Scheduled(fixedRate = 10000)
	public void performTask() {

		System.out.println("Regular task performed at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void performDelayedTask() {

		System.out.println("Delayed Regular task performed at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(cron = "*/5 * * * * *")
	public void performTaskUsingCron() {

		System.out.println("Regular task performed using Cron at "
				+ dateFormat.format(new Date()));

	}

}
