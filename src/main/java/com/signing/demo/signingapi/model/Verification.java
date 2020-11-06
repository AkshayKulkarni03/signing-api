package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Verification {

    @JsonIgnore
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonProperty("Type")
    private String type;
    @JsonProperty("RequireHandsignature")
    private Boolean requireHandsignature;
    @JsonProperty("ScribbleNameFixed")
    private Boolean scribbleNameFixed;

    @ManyToOne(optional = false)
    @JoinColumn(name="signer_request_id")
    @JsonIgnore
    private SignerRequest signerRequest;
}
