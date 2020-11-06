package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerRequest {

    @JsonIgnore
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String email;
    private String mobile;
    private Boolean requireScribble;
    private Boolean sendSignRequest;
    private String signRequestMessage;
    private Integer daysToRemind;

    @OneToMany(mappedBy = "signerRequest", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Activities> activities = new ArrayList<>();

    @OneToMany(mappedBy = "signerRequest", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Verification> verifications = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "signer_details_id")
    @JsonIgnore
    private SignerDetails signerDetails;

}
