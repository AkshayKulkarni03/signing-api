package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Activities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Integer code;
    private String activities;
    private String info;
    private String creationDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "signer_request_id")
    @JsonIgnore
    private SignerRequest signerRequest;
}
