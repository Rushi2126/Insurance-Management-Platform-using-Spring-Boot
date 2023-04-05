package com.rushi.insurance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.insurance.exceptions.ResourceNotFoundException;
import com.rushi.insurance.models.Claim;
import com.rushi.insurance.repository.ClaimRepository;
import com.rushi.insurance.services.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public List<Claim> getAllClaims() {
		return claimRepository.findAll();
	}

	@Override
	public Claim getClaimById(Long id) {
		Optional<Claim> optionalClaim = claimRepository.findById(id);
		if (optionalClaim.isPresent()) {
			return optionalClaim.get();
		} else {
			throw new ResourceNotFoundException("Claim not found with id " + id);
		}
	}

	@Override
	public Claim createClaim(Claim claim) {
		try {
			return claimRepository.save(claim);
		} catch (Exception ex) {
			throw new RuntimeException("Error occurred while creating claim: " + ex.getMessage());
		}
	}

	@Override
	public Claim updateClaim(Long id, Claim claim) {
		Optional<Claim> optionalClaim = claimRepository.findById(id);
		if (optionalClaim.isPresent()) {
			Claim existingClaim = optionalClaim.get();
			existingClaim.setClaimNumber(claim.getClaimNumber());
			existingClaim.setDescription(claim.getDescription());
			existingClaim.setClaimDate(claim.getClaimDate());
			existingClaim.setClaimStatus(claim.getClaimStatus());
			existingClaim.setInsurancePolicy(claim.getInsurancePolicy());

			try {
				return claimRepository.save(existingClaim);
			} catch (Exception ex) {
				throw new RuntimeException("Error occurred while updating claim: " + ex.getMessage());
			}
		} else {
			throw new ResourceNotFoundException("Claim not found with id " + id);
		}
	}

	@Override
	public void deleteClaim(Long id) {
		Optional<Claim> optionalClaim = claimRepository.findById(id);
		if (optionalClaim.isPresent()) {
			claimRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Claim not found with id " + id);
		}
	}

}
