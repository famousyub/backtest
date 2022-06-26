package com.omnia.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.omnia.app.exception.BadRequestException;
import com.omnia.app.exception.DuplicateResourceException;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Company;
import com.omnia.app.model.Employee;
import com.omnia.app.model.Equipement;
import com.omnia.app.model.Network;
import com.omnia.app.payload.NetworkDetailsResponse;
import com.omnia.app.payload.NetworkListResponse;
import com.omnia.app.payload.NetworkRequest;
import com.omnia.app.payload.NetworkResponse;
import com.omnia.app.payload.PagedResponse;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.NetworkRepository;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.util.AppConstants;
import com.omnia.app.util.NetworkMapper;

@Service
public class NetworkService {

	private static final Logger logger = LoggerFactory.getLogger(NetworkService.class);

	@Autowired
	private NetworkRepository networkRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	public PagedResponse<NetworkListResponse> getAllNetworksByCompany(Long companyID, int page, int size) {

		validatePageNumberAndSize(page, size);

		// Retrieve all networks created for the given company
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		logger.info("companyID : ", companyID);
		Company company = companyRepository.findById(companyID)
				.orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyID));

		Page<Network> networks = networkRepository.findByCompany(company, pageable);

		if (networks.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), networks.getNumber(), networks.getSize(),
					networks.getTotalElements(), networks.getTotalPages(), networks.isLast());
		}

		List<NetworkListResponse> networkListResponses = networks.map(network -> {

			// Retrieve creator details
			Employee creator = userRepository.findById(network.getCreatedBy()).orElse(null);

			return NetworkMapper.mapNetworkToNetworkListResponse(network, creator);
		}).getContent();

		return new PagedResponse<>(networkListResponses, networks.getNumber(), networks.getSize(),
				networks.getTotalElements(), networks.getTotalPages(), networks.isLast());
	}

	public NetworkDetailsResponse getNetworkDetails(Long networkId) {

		Network networkModel = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		Employee creator = userRepository.findById(networkModel.getCreatedBy())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", networkModel.getCreatedBy()));
		
		

		return NetworkMapper.mapNetworkToNetworkDetailsResponse(networkModel, creator);
	}

	public NetworkResponse getNetworkById(Long networkId) {

		Network network = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		return NetworkMapper.mapNetworkToNetworkResponse(network);
	}

	public Network createNetwork(NetworkRequest networkRequest, Long companyID) {

		Company company = companyRepository.findById(companyID)
				.orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyID));

		Optional<Network> network = networkRepository.findByReference(networkRequest.getReference());

		if (!network.isPresent()) {
			Network networkModel = new Network();
			networkModel.setReference(networkRequest.getReference());
			networkModel.setCountry(networkRequest.getCountry());
			networkModel.setGpsLat(networkRequest.getGpsLat());
			networkModel.setGpsLong(networkRequest.getGpsLong());
			networkModel.setCompany(company);

			return networkRepository.save(networkModel);
		}
		throw new DuplicateResourceException("Network", "reference");
	}

	public Network updateNetwork(Long networkId, NetworkRequest networkRequest) {

		Network networkModel = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		Optional<Network> network = networkRepository.findByReference(networkRequest.getReference());

		if (!(network.isPresent()) || networkModel.getReference().equals(networkRequest.getReference())) {
			networkModel.setReference(networkRequest.getReference());
			networkModel.setCountry(networkRequest.getCountry());
			networkModel.setGpsLat(networkRequest.getGpsLat());
			networkModel.setGpsLong(networkRequest.getGpsLong());
			return networkRepository.save(networkModel);
		}
		throw new DuplicateResourceException("Network", "reference");
	}

	public int countEquipements(Long networkId) {

		Network network = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		return network.getEquipements().size();
	}

	public void deleteNetwork(Long networkId) {

		networkRepository.deleteById(networkId);
	}

	public void moveEquipements(Long oldNetworkId, Long newNetworkId) {

		Network oldNetwork = networkRepository.findById(oldNetworkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", oldNetworkId));

		Network newNetwork = networkRepository.findById(newNetworkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", newNetworkId));

		for (Equipement eq : oldNetwork.getEquipements()) {
			newNetwork.getEquipements().add(eq);
			eq.setNetwork(newNetwork);
		}

		oldNetwork.getEquipements().clear();
		networkRepository.save(newNetwork);

	}

	public void deleteNetwork(Long networkId, Long toNetworkId) {

		Network oldNetwork = networkRepository.findById(networkId)
				.orElseThrow(() -> new ResourceNotFoundException("Network", "id", networkId));

		if (toNetworkId == 0) {
			networkRepository.deleteById(networkId);
		} else {
			Network newNetwork = networkRepository.findById(toNetworkId)
					.orElseThrow(() -> new ResourceNotFoundException("Network", "id", toNetworkId));

			moveEquipements(networkId, toNetworkId);
			networkRepository.deleteById(networkId);

		}
	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > AppConstants.MAX_PAGE_SIZE) {
			throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
		}
	}
}
