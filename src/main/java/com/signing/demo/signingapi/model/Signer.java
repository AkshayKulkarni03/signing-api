package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Signer {
    @JsonProperty("Id")
    public String id;
    @JsonProperty("Expires")
    public Object expires;
    @JsonProperty("Email")
    public String email;
    @JsonProperty("Authentications")
    public List<Object> authentications;
    @JsonProperty("Verifications")
    public List<Verification> verifications;
    @JsonProperty("Mobile")
    public Object mobile;
    @JsonProperty("Iban")
    public Object iban;
    @JsonProperty("BSN")
    public Object bSN;
    @JsonProperty("RequireScribbleName")
    public Boolean requireScribbleName;
    @JsonProperty("RequireScribble")
    public Boolean requireScribble;
    @JsonProperty("RequireEmailVerification")
    public Boolean requireEmailVerification;
    @JsonProperty("RequireSmsVerification")
    public Boolean requireSmsVerification;
    @JsonProperty("RequireIdealVerification")
    public Boolean requireIdealVerification;
    @JsonProperty("RequireDigidVerification")
    public Boolean requireDigidVerification;
    @JsonProperty("RequireKennisnetVerification")
    public Boolean requireKennisnetVerification;
    @JsonProperty("RequireSurfnetVerification")
    public Boolean requireSurfnetVerification;
    @JsonProperty("SendSignRequest")
    public Boolean sendSignRequest;
    @JsonProperty("SendSignConfirmation")
    public Boolean sendSignConfirmation;
    @JsonProperty("SignRequestMessage")
    public String signRequestMessage;
    @JsonProperty("DaysToRemind")
    public Integer daysToRemind;
    @JsonProperty("Language")
    public String language;
    @JsonProperty("ScribbleName")
    public Object scribbleName;
    @JsonProperty("ScribbleNameFixed")
    public Boolean scribbleNameFixed;
    @JsonProperty("Reference")
    public Object reference;
    @JsonProperty("IntroText")
    public Object introText;
    @JsonProperty("ReturnUrl")
    public Object returnUrl;
    @JsonProperty("Activities")
    public List<Object> activities;
    @JsonProperty("RejectReason")
    public Object rejectReason;
    @JsonProperty("SignUrl")
    public String signUrl;
    @JsonProperty("SignedDateTime")
    public Object signedDateTime;
    @JsonProperty("RejectDateTime")
    public Object rejectDateTime;
    @JsonProperty("CreatedDateTime")
    public String createdDateTime;
    @JsonProperty("ModifiedDateTime")
    public String modifiedDateTime;
    @JsonProperty("Context")
    public Object context;
}
