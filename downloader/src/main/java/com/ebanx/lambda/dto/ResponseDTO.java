package com.ebanx.lambda.dto;

public class ResponseDTO {

    public String url;

    public ResponseDTO() {
    }

    public ResponseDTO(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "url='" + url + '\'' + '}';
    }
}
