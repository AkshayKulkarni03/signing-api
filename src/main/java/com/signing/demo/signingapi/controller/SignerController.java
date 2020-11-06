package com.signing.demo.signingapi.controller;

import com.signing.demo.signingapi.model.SignerDetails;
import com.signing.demo.signingapi.repository.SigningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/signer")
public class SignerController {

    @Autowired
    private SigningRepository signingRepository;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveSignerData(@RequestBody SignerDetails signerDetails) {

        signerDetails.getSigners().replaceAll(signer -> {
            signer.setSignerDetails(signerDetails);
            signer.getVerifications().replaceAll(verification -> {
                verification.setSignerRequest(signer);
                return verification;
            });
            signer.getActivities().replaceAll(activitiy -> {
                activitiy.setSignerRequest(signer);
                return activitiy;
            });
            return signer;
        });

        signingRepository.save(signerDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SignerDetails>> getAllSigningRequests() {
        return ResponseEntity.ok(signingRepository.findAll());
    }

}
