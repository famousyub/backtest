package com.omnia.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.service.DatabaseRecoveryService;

@RestController
@RequestMapping("/api/dbRecovery")
public class DatabaseRecoveryController {

	@Autowired
	private DatabaseRecoveryService dbRecoveryService;

	@GetMapping()
	public ResponseEntity<?> saveDatabaseRecovery() {
		try {

			dbRecoveryService.saveDatabaseRecovery();
			return ResponseEntity.ok().body(new ApiResponse(true, "Database Saved Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (BadRequestException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@GetMapping("/all")
	public List<String> getNetworks() {
		return dbRecoveryService.getAllDbRecoveries();
	}

	@DeleteMapping("/{dbRecoveryName}")
	public ResponseEntity<?> deleteDatabaseRecovery(@PathVariable String dbRecoveryName) {
		try {
			dbRecoveryService.deleteDatabaseRecovery(dbRecoveryName);
			return ResponseEntity.ok().body(new ApiResponse(true, "Database Recovery Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@GetMapping("/{dbRecoveryName}")
	public ResponseEntity<?> restoreDatabaseRecovery(@PathVariable String dbRecoveryName) {
		try {
			dbRecoveryService.restoreDatabaseRecovery(dbRecoveryName);
			return ResponseEntity.ok().body(new ApiResponse(true, "Database Recovery Restored Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

}