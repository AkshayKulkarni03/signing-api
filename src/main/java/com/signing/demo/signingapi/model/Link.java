package com.signing.demo.signingapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link {

    @JsonProperty("Rel")
    private String rel;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Link")
    private String link;
}
