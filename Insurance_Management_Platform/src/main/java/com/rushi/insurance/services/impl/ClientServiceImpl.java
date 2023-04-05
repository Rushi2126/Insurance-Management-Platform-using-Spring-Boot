package com.rushi.insurance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.exceptions.ResourceNotFoundException;
import com.rushi.insurance.models.Client;
import com.rushi.insurance.repository.ClientRepository;
import com.rushi.insurance.services.ClientService;
import com.rushi.insurance.validators.ClientValidator;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientValidator clientValidator;

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(Long id) throws ResourceNotFoundException {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (optionalClient.isPresent()) {
			return optionalClient.get();
		} else {
			throw new ResourceNotFoundException("Client not found with id " + id);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Client createClient(Client client) throws BadRequestException {
		clientValidator.validateClient(client);
		if (StringUtils.isEmpty(client.getName()) || StringUtils.isEmpty(client.getDateOfBirth())
				|| StringUtils.isEmpty(client.getAddress()) || StringUtils.isEmpty(client.getPhoneNumber())) {
			throw new BadRequestException("Please provide all required fields");
		}
		try {
			return clientRepository.save(client);
		} catch (Exception ex) {
			throw new RuntimeException("Error occurred while creating client: " + ex.getMessage());
		}
	}

	@Override
	public Client updateClient(Long id, Client client) throws BadRequestException, ResourceNotFoundException {
		clientValidator.validateClient(client);
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (optionalClient.isPresent()) {
			Client existingClient = optionalClient.get();
			existingClient.setName(client.getName());
			existingClient.setDateOfBirth(client.getDateOfBirth());
			existingClient.setAddress(client.getAddress());
			existingClient.setPhoneNumber(client.getPhoneNumber());

			try {
				return clientRepository.save(existingClient);
			} catch (Exception ex) {
				throw new RuntimeException("Error occurred while updating client: " + ex.getMessage());
			}
		} else {
			throw new ResourceNotFoundException("Client not found with id " + id);
		}
	}

	@Override
	public void deleteClient(Long id) throws ResourceNotFoundException {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (optionalClient.isPresent()) {
			clientRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Client not found with id " + id);
		}
	}

}