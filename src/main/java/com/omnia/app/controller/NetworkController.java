package com.omnia.app.controller;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.DuplicateResourceException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Network;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.NetworkDetailsResponse;
import com.omnia.app.payload.NetworkListResponse;
import com.omnia.app.payload.NetworkRequest;
import com.omnia.app.payload.NetworkResponse;
import com.omnia.app.payload.PagedResponse;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.NetworkService;
import com.omnia.app.util.AppConstants;

@RestController
@RequestMapping("/api/networks")
public class NetworkController {

	@Autowired
	private NetworkService networkService;

	@GetMapping("/{companyId}")
	public PagedResponse<NetworkListResponse> getNetworks(@CurrentUser UserPrincipal currentUser,
			@PathVariable Long companyId,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return networkService.getAllNetworksByCompany(companyId, page, size);
	}

	@GetMapping("/details/{networkId}")
	public ResponseEntity<?> getNetworkDetails(@CurrentUser UserPrincipal currentUser, @PathVariable long networkId) {
		try {
			NetworkDetailsResponse networkDetailsResponse = networkService.getNetworkDetails(networkId);

			return ResponseEntity.ok(networkDetailsResponse);

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Network not found"));
		}
	}

	@GetMapping("/byId/{networkId}")
	public ResponseEntity<?> getNetworkById(@CurrentUser UserPrincipal currentUser, @PathVariable Long networkId) {
		try {
			NetworkResponse networkResponse = networkService.getNetworkById(networkId);
			return ResponseEntity.ok(networkResponse);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@PostMapping("/{companyId}")
	public ResponseEntity<?> createNetwork(@CurrentUser UserPrincipal currentUser, @PathVariable Long companyId,
			@Valid @RequestBody NetworkRequest networkRequest) {

		try {
			Network network = networkService.createNetwork(networkRequest, companyId);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{networkId}")
					.buildAndExpand(network.getId()).toUri();
			return ResponseEntity.created(location).body(new ApiResponse(true, "Network Created Successfully"));
		} catch (DuplicateResourceException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@PutMapping("/{networkId}")
	public ResponseEntity<?> updateNetwork(@CurrentUser UserPrincipal currentUser, @PathVariable long networkId,
			@Valid @RequestBody NetworkRequest networkRequest) {

		try {
			Network network = networkService.updateNetwork(networkId, networkRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{networkId}")
					.buildAndExpand(network.getId()).toUri();
			return ResponseEntity.created(location).body(new ApiResponse(true, "Network Updated Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse(false, "A Network with the same reference exists already"));
		}

	}

	@GetMapping("move/{oldNetworkId}/{newNetworkId}")
	public ResponseEntity<?> moveEquipements(@PathVariable long oldNetworkId, @PathVariable long newNetworkId) {

		try {
			if (networkService.countEquipements(oldNetworkId) > 0) {
				networkService.moveEquipements(oldNetworkId, newNetworkId);
				return ResponseEntity.ok()
						.body(new ApiResponse(true,
								"Equipments moved Successfully from "
										+ networkService.getNetworkById(oldNetworkId).getReference() + " to "
										+ networkService.getNetworkById(newNetworkId).getReference()));
			} else {
				return ResponseEntity.ok()
						.body(new ApiResponse(true, networkService.getNetworkById(oldNetworkId).getReference()
								+ " does not contain any attached equipment to be moved"));
			}
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Unable to move Equipments"));
		}
	}

	@DeleteMapping("/{networkId}/{toNetworkId}")
	public ResponseEntity<?> deleteNetwork(@PathVariable long networkId, @PathVariable long toNetworkId) {

		try {
			networkService.deleteNetwork(networkId, toNetworkId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Network Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Unable to delete Network"));
		}
	}
}