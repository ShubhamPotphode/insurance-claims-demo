package com.example.insurance.service;

import com.example.insurance.model.Claim;
import com.example.insurance.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final KieContainer kieContainer;

    public Claim processClaim(Claim claim) {
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.setGlobal("today", LocalDate.now());

        kieSession.insert(claim);
        kieSession.fireAllRules();
        kieSession.dispose();

        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
