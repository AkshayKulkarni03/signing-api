package com.signing.demo.signingapi.repository;

import com.signing.demo.signingapi.model.SignerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SigningRepository extends JpaRepository<SignerDetails, UUID> {
}
