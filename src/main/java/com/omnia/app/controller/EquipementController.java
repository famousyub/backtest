package com.omnia.app.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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
import com.omnia.app.service.EquipementService;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

	@Autowired
	private EquipementService equipementService;

	@DeleteMapping("/{equipementId}")
	public ResponseEntity<?> deleteEquipement(@PathVariable long equipementId) {
		try {
			equipementService.deleteEquipement(equipementId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Equipement Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@GetMapping("move/network/{equipementId}/{networkId}")
	public ResponseEntity<?> editEquipementNetwork(@PathVariable Long equipementId, @PathVariable Long networkId) {
		try {
			equipementService.editEquipementNetwork(equipementId, networkId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Equipement Moved Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (BadRequestException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@GetMapping("/byId/{equipementId}")
	public ResponseEntity<?> getEquipementById(@CurrentUser UserPrincipal currentUser,
			@PathVariable Long equipementId) {
		try {
			EquipementResponse equipementResponse = equipementService.getEquipementById(equipementId);
			return ResponseEntity.ok(equipementResponse);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@PostMapping("/{networkId}")
	public ResponseEntity<?> createEquipement(@CurrentUser UserPrincipal currentUser, @PathVariable Long networkId,
			@Valid @RequestBody EquipementRequest equipementRequest) {

		try {
			Equipement equipement = equipementService.createEquipement(equipementRequest, networkId);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{equipementId}")
					.buildAndExpand(equipement.getId()).toUri();
			return ResponseEntity.created(location)
					.body(new ApiResponse(true, equipement.getId(), "Base Station Created Successfully"));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body(
					new ApiResponse(false, "An Equipement with the same name or the same ip address exists already"));
		}

	}

	@PutMapping("/{equipementId}")
	public ResponseEntity<?> updateEquipement(@PathVariable long equipementId,
			@Valid @RequestBody EquipementRequest equipementRequest) {
		try {
			System.out.println("here_put");
			Equipement equipement = equipementService.updateEquipement(equipementId, equipementRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{equipementId}")
					.buildAndExpand(equipement.getId()).toUri();
			return ResponseEntity.created(location).body(new ApiResponse(true, "Equipement Updated Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body(
					new ApiResponse(false, "An Equipement with the same name or the same ip address exists already"));
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAll() {

		equipementService.deleteAll();
		return ResponseEntity.ok().body(new ApiResponse(true, "Equipement Deleted Successfully"));

	}

	@PostMapping("/saveAll/{networkId}")
	public void addEquipementList(@RequestBody List<EquipementRequest> BSList, @PathVariable Long networkId) {
		equipementService.addEquipementList(BSList, networkId);
	}

}