package com.rushi.insurance.services;

import java.util.List;

import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.exceptions.ResourceNotFoundException;
import com.rushi.insurance.models.InsurancePolicy;

public interface InsurancePolicyService {
	

public List<InsurancePolicy> getAllInsurancePolicies();

public InsurancePolicy getInsurancePolicyById(Long id);

public InsurancePolicy createInsurancePolicy(InsurancePolicy insurancePolicy) throws BadRequestException;

public InsurancePolicy updateInsurancePolicy(Long id, InsurancePolicy insurancePolicy) throws ResourceNotFoundException, BadRequestException;

public void deleteInsurancePolicy(Long id) throws ResourceNotFoundException;

}