package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SigningOverview {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Files")
    private FileEntry files;
    @JsonProperty("Seal")
    private Boolean seal;
    @JsonProperty("Signers")
    private List<Signer> signers;
    @JsonProperty("Receivers")
    private List<Object> receivers;
    @JsonProperty("Reference")
    private Object reference;
    @JsonProperty("PostbackUrl")
    private Object postbackUrl;
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
    private Object context;
}
