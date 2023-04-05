package com.rushi.insurance.validators;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.models.InsurancePolicy;


@Component
public class InsurancePolicyValidator {

    public static void validateInsurancePolicy(InsurancePolicy policy) throws BadRequestException {
        if (policy == null) {
            throw new BadRequestException("Insurance policy cannot be null.");
        }
        if (policy.getPolicyNumber() == null || policy.getPolicyNumber().isEmpty()) {
            throw new BadRequestException("Policy number cannot be empty.");
        }
        if (policy.getPolicyType() == null || policy.getPolicyType().isEmpty()) {
            throw new BadRequestException("Policy type cannot be empty.");
        }
        if (policy.getCoverageAmount() == null || policy.getCoverageAmount() < 0) {
            throw new BadRequestException("Coverage amount cannot be null or negative.");
        }
        if (policy.getPremium() == null || policy.getPremium() < 0) {
            throw new BadRequestException("Premium cannot be null or negative.");
        }
        if (policy.getStartDate() == null || policy.getStartDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Start date cannot be null or before today.");
        }
        if (policy.getEndDate() == null || policy.getEndDate().isBefore(policy.getStartDate())) {
            throw new BadRequestException("End date cannot be null or before start date.");
        }
    }
}
