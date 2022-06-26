package com.omnia.app.controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PingController {

	
	@GetMapping("/ping")
	public ResponseEntity<?> getUsersIp(){
		
		HashMap<String, String> m =new HashMap<>();
		
		try {
			
			InetAddress ip = InetAddress.getLocalHost();
			String user_ip= ip.getCanonicalHostName();
			String con_ip = ip.getHostAddress();
			String con_adre =ip.getCanonicalHostName();
			m.put("ip", user_ip);
			m.put("con_ip", con_ip);
			m.put("con_adree", con_adre);
			
			return  ResponseEntity.ok().body(m);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.toString());
			
		}
		 
	}
	
	@GetMapping("/ping/v6")
	public ResponseEntity<?> getUsersIpv6(){
		
		HashMap<String, String> m =new HashMap<>();
		
		try {
			
			InetAddress ip = InetAddress.getLocalHost();
			String user_ip= ip.getCanonicalHostName();
			String con_ip = ip.getHostAddress();
			String con_adre =ip.getCanonicalHostName();
			m.put("ip", user_ip);
			m.put("con_ip", con_ip);
			m.put("con_adree", con_adre);
			
			return  ResponseEntity.ok().body(m);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.toString());
			
		}
		 
	}
}
