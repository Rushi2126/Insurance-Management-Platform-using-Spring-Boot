package com.rushi.insurance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.exceptions.ResourceNotFoundException;
import com.rushi.insurance.models.InsurancePolicy;
import com.rushi.insurance.repository.InsurancePolicyRepository;
import com.rushi.insurance.services.InsurancePolicyService;
import com.rushi.insurance.validators.InsurancePolicyValidator;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public List<InsurancePolicy> getAllInsurancePolicies() {
        return insurancePolicyRepository.findAll();
    }

    @Override
    public InsurancePolicy getInsurancePolicyById(Long id) throws ResourceNotFoundException {
        Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            return optionalPolicy.get();
        } else {
            throw new ResourceNotFoundException("Insurance policy", "id", id);
        }
    }



    @Override
    public InsurancePolicy createInsurancePolicy(InsurancePolicy policy) throws BadRequestException {
        InsurancePolicyValidator.validateInsurancePolicy(policy);
        if (insurancePolicyRepository.existsByPolicyNumber(policy.getPolicyNumber())) {
            throw new BadRequestException("A policy with this policy number already exists");
        } else {
            return insurancePolicyRepository.save(policy);
        }
    }

    @Override
    public InsurancePolicy updateInsurancePolicy(Long id, InsurancePolicy policy) throws ResourceNotFoundException, BadRequestException {
        Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            InsurancePolicy existingPolicy = optionalPolicy.get();
            existingPolicy.setPolicyType(policy.getPolicyType());
            existingPolicy.setCoverageAmount(policy.getCoverageAmount());
            existingPolicy.setPremium(policy.getPremium());
            existingPolicy.setStartDate(policy.getStartDate());
            existingPolicy.setEndDate(policy.getEndDate());
            InsurancePolicyValidator.validateInsurancePolicy(existingPolicy);
            return insurancePolicyRepository.save(existingPolicy);
        } else {
            throw new ResourceNotFoundException("Insurance policy", "id", id);
        }
    }

    @Override
    public void deleteInsurancePolicy(Long id) throws ResourceNotFoundException {
        Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            insurancePolicyRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Insurance policy", "id", id);
        }
    }
}
