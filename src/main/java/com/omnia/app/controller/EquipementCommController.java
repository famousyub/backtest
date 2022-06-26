package com.omnia.app.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Equipement;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.EquipementRequest;
import com.omnia.app.payload.EquipementResponse;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.EquipementCommService;
import com.omnia.app.service.EquipementService;
import com.omnia.app.util.EquipementCommMapper;

@RestController
@RequestMapping("/api/equipements/comm")
@CrossOrigin(origins = "*")
public class EquipementCommController {

	@Autowired
	private EquipementCommService equipementCommService;

	@GetMapping("/byIpAddress/{state}/{macAddress}/{firmwareVersion}")
	public ResponseEntity<?> getEquipementById(HttpServletRequest request,@PathVariable String state,@PathVariable String macAddress,@PathVariable String firmwareVersion) {
		try {
			String requestIpAddr = request.getRemoteAddr();
			System.out.println("get request from "+requestIpAddr);
			
			Equipement equipement = equipementCommService.updateEquipementComm(requestIpAddr, state, macAddress,firmwareVersion);
			
			System.out.println(equipement.toString());

			return ResponseEntity.ok(EquipementCommMapper.mapEquipementToEquipementCommResponse(equipement));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
		
		
	
	}


}