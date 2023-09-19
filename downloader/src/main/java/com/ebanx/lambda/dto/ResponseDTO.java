package com.ebanx.lambda.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ResponseDTO {

    private final String url;

    public ResponseDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
