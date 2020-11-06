package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Files")
    private Map<String, FileEntry> files;
    @JsonProperty("Seal")
    private Boolean seal;
    @JsonProperty("Signers")
    private List<Signer> signers;
    @JsonProperty("Receivers")
    private List<String> receivers;
    @JsonProperty("Reference")
    private String reference;
    @JsonProperty("PostbackUrl")
    private String postbackUrl;
    @JsonProperty("SignRequestMode")
    private Integer signRequestMode;
    @JsonProperty("DaysToExpire")
    private Integer daysToExpire;
    @JsonProperty("SendEmailNotifications")
    private Boolean sendEmailNotifications;
    @JsonProperty("CreatedDateTime")
    private String createdDateTime;
    @JsonProperty("ModifiedDateTime")
    private String modifiedDateTime;
    @JsonProperty("CanceledDateTime")
    private String canceledDateTime;
    @JsonProperty("Context")
    private String context;
}
