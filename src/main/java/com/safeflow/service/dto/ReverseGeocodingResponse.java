package com.safeflow.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReverseGeocodingResponse {
    @JsonProperty("display_name")
    private String displayName;
}