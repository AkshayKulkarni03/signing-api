package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SigningOverviewRequest {
    private Boolean sendEmailNotifications;

    @OneToMany(mappedBy = "signerDetails", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<SignerRequest> signers = new ArrayList<>();
}
