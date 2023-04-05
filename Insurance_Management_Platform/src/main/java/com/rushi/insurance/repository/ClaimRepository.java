package com.rushi.insurance.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.insurance.models.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
