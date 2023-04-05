package com.rushi.insurance.validators;

import org.springframework.stereotype.Component;

import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.models.Claim;

@Component
public class ClaimValidator {

    public void validateClaim(Claim claim) throws BadRequestException {
        if (claim == null) {
            throw new BadRequestException("Claim object cannot be null");
        }

        if (claim.getClaimNumber() == null || claim.getClaimNumber().isEmpty()) {
            throw new BadRequestException("Claim number cannot be empty or null");
        }

        if (claim.getDescription() == null || claim.getDescription().isEmpty()) {
            throw new BadRequestException("Description cannot be empty or null");
        }

        if (claim.getClaimDate() == null) {
            throw new BadRequestException("Claim date cannot be empty or null");
        }

        if (claim.getClaimStatus() == null) {
            throw new BadRequestException("Claim status cannot be null");
        }
    }
}
