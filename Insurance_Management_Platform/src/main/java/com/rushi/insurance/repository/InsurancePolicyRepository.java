package com.rushi.insurance.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.insurance.models.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
	 boolean existsByPolicyNumber(String policyNumber);
}
