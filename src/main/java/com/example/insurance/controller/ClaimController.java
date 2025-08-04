package com.example.insurance.controller;

import com.example.insurance.model.Claim;
import com.example.insurance.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    //Submit a new claim
    @PostMapping
    public ResponseEntity<Claim> submitClaim(@RequestBody Claim claim) {
        Claim processedClaim = claimService.processClaim(claim);
        return  ResponseEntity.ok(processedClaim);
    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }
}
