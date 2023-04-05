package com.rushi.insurance.services;

import java.util.List;

import com.rushi.insurance.models.Claim;

public interface ClaimService {
	
	//get all
    List<Claim> getAllClaims();
    
    //get
    Claim getClaimById(Long id);
    
    //create
    Claim createClaim(Claim claim);
    
    //update
    Claim updateClaim(Long id, Claim claim);
    
    //delete
    void deleteClaim(Long id);
}
