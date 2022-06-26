package com.omnia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.entity.Notification;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;

@RestController
@RequestMapping("/api/employeeserver")
public class ServeursemployeeContoller {
	
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@GetMapping("/notify")
	 public String getNotification(@CurrentUser UserPrincipal currentUser) {
		
		Notification notifications =new Notification();
	     String username = "user";
	     notifications.increment();
	   //  logger.info("counter" + notifications.getCount() + "" + principal.getName());
	     //  logger.info("usersend:"+sha.getUser().getName()) ; //user
	     messagingTemplate.convertAndSendToUser(currentUser.getUsername(), "queue/notification", notifications);
	     return "Notifications successfully sent to Angular !";
	 }
	 

}
