package com.rushi.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.rushi.insurance.models.Claim;
import com.rushi.insurance.services.ClaimService;
import com.rushi.insurance.validators.ClaimValidator;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;
    

    @Autowired
    private ClaimValidator claimValidator;

    // Get all claims
    @GetMapping("")
    public ResponseEntity<List<Claim>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }

    // Get claim by ID
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable("id") Long id) {
        Claim claim = claimService.getClaimById(id);
        if (claim == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(claim);
    }

    @PostMapping("")
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim, BindingResult result) throws BadRequestException {
        claimValidator.validateClaim(claim);
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            throw new BadRequestException(errors.get(0));
        }
        Claim newClaim = claimService.createClaim(claim);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClaim);
    }



    // Update claim
    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable("id") Long id, @Valid @RequestBody Claim claim, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(null);
        }
        Claim updatedClaim = claimService.updateClaim(id, claim);
        if (updatedClaim == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedClaim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable Long id) throws ResourceNotFoundException {
    	claimService.deleteClaim(id);
        return ResponseEntity.ok("Deleted claim with id: " + id);
    }



    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleInvalidRequest(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleInternalError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
