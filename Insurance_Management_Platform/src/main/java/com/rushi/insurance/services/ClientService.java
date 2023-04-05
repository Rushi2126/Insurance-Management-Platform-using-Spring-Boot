package com.rushi.insurance.services;

import java.util.List;

import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.exceptions.ResourceNotFoundException;
import com.rushi.insurance.models.Client;

public interface ClientService {
	// Get all clients
	List<Client> getAllClients();
    
	// Get a client by ID
	Client getClientById(Long id) throws ResourceNotFoundException;
    
	// Create a new client
	Client createClient(Client client) throws BadRequestException;
    
	// Update an existing client
	Client updateClient(Long id, Client client) throws BadRequestException, ResourceNotFoundException;
    
	// Delete a client by ID
	void deleteClient(Long id) throws ResourceNotFoundException;
}
