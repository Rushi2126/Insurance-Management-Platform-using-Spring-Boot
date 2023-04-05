package com.rushi.insurance.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rushi.insurance.exceptions.BadRequestException;
import com.rushi.insurance.exceptions.ResourceNotFoundException;
import com.rushi.insurance.models.InsurancePolicy;
import com.rushi.insurance.services.InsurancePolicyService;
import com.rushi.insurance.validators.InsurancePolicyValidator;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

    private InsurancePolicyService insurancePolicyService;
    private InsurancePolicyValidator insurancePolicyValidator;

    @Autowired
    public InsurancePolicyController(InsurancePolicyService insurancePolicyService, InsurancePolicyValidator insurancePolicyValidator) {
        this.insurancePolicyService = insurancePolicyService;
        this.insurancePolicyValidator = insurancePolicyValidator;
    }

    @GetMapping("")
    public ResponseEntity<List<InsurancePolicy>> getAllInsurancePolicies() {
        List<InsurancePolicy> policies = insurancePolicyService.getAllInsurancePolicies();
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicy> getInsurancePolicyById(@PathVariable Long id) {
        InsurancePolicy policy = insurancePolicyService.getInsurancePolicyById(id);
        return ResponseEntity.ok(policy);
    }

    @SuppressWarnings("static-access")
	@PostMapping("")
    public ResponseEntity<InsurancePolicy> createInsurancePolicy(@RequestBody InsurancePolicy policy, BindingResult bindingResult) throws BadRequestException {
        insurancePolicyValidator.validateInsurancePolicy(policy);
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        InsurancePolicy newPolicy = insurancePolicyService.createInsurancePolicy(policy);
        return ResponseEntity.ok(newPolicy);
    }

    @SuppressWarnings("static-access")
	@PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updateInsurancePolicy(@PathVariable Long id, @RequestBody InsurancePolicy policy, BindingResult bindingResult) throws ResourceNotFoundException, BadRequestException {
        insurancePolicyValidator.validateInsurancePolicy(policy);
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        InsurancePolicy updatedPolicy = insurancePolicyService.updateInsurancePolicy(id, policy);
        return ResponseEntity.ok(updatedPolicy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInsurancePolicy(@PathVariable Long id) throws ResourceNotFoundException {
    	insurancePolicyService.deleteInsurancePolicy(id);
        return ResponseEntity.ok("Deleted insurance policy with id: " + id);
    }
    
    
 // Exception Handlers

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid URL or ID Provided");
    }
}
