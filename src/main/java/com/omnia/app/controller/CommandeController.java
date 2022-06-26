package com.omnia.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Commande;
import com.omnia.app.model.Company;
import com.omnia.app.payload.ApiResponse;
import com.omnia.app.payload.CommandeListResponse;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.CommandeService;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

	@Autowired
	private CommandeService commandeService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@PutMapping("/create/{companyId}")
	public ResponseEntity<?> createCommande(@Valid @RequestBody Commande commandeRequest, @PathVariable long companyId) {

		try {
			
			 Company company = companyRepository.findById(companyId).orElse(null);
			
			commandeRequest.setCompany(company);
			Commande commande = commandeService.createCommande(commandeRequest);

		//	return ResponseEntity.ok().body(new ApiResponse(true, commande.getId(), "Commande Created Successfully"));
			return ResponseEntity.ok().body(commande);

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}

	}

	@PutMapping("/{commandeId}")
	public ResponseEntity<?> updateCommande(@Valid @RequestBody Commande commandeRequest,
			@PathVariable long commandeId) {

		try {
			Commande commande = commandeService.updateCommande(commandeRequest, commandeId);

			return ResponseEntity.ok().body(new ApiResponse(true, commande.getId(), "Commande Updated Successfully"));

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@DeleteMapping("/{commandeId}")
	public ResponseEntity<?> deleteCommande(@PathVariable long commandeId) {
		try {
			commandeService.deleteCommande(commandeId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Commande Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
		}
	}

	@GetMapping("/all")
	public List<CommandeListResponse> getCommandes() {
		return commandeService.getCommandes();
	}
	
	@GetMapping("/byCompany")
	public List<CommandeListResponse> getCommandesByCompany(@CurrentUser UserPrincipal currentUser) {
		Company userCompany = userRepository.getOne(currentUser.getId()).getCompany();
		return commandeService.getCommandesByCompany(userCompany);
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteAllCommandes() {

		commandeService.deleteAllCommandes();
		return ResponseEntity.ok().body(new ApiResponse(true, "All Commandes Deleted Successfully"));

	}
	
	@DeleteMapping("/byCompany")
	public ResponseEntity<?> deleteCommandesByCompany(@CurrentUser UserPrincipal currentUser) {

		Company company = userRepository.getOne(currentUser.getId()).getCompany();

		commandeService.deleteCommandesByCompany(company);
		return ResponseEntity.ok().body(new ApiResponse(true, "All Commandes Deleted Successfully"));

	}

	@GetMapping("/{commandeId}/associateEquipement")
	public ResponseEntity<?> associateEquipement(@PathVariable long commandeId) {

		Commande commande = commandeService.associateEquipementToCommande(commandeId);

		return ResponseEntity.ok()
				.body(new ApiResponse(true, commande.getId(), "Commande associated to Equipement Successfully"));

	}

	@GetMapping("/{commandeId}/disassociateEquipement")
	public ResponseEntity<?> disassociateEquipement(@PathVariable long commandeId) {

		try {
			Commande commande = commandeService.disassociateEquipementFromCommande(commandeId);
			return ResponseEntity.ok().body(new ApiResponse(true, "Commande Deleted Successfully"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "No equipement can be used"));
		}

	}

}