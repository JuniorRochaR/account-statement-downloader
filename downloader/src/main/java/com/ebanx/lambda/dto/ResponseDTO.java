package com.ebanx.lambda.dto;

public class ResponseDTO {

    private String url;

    public ResponseDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "url='" + url + '\'' + '}';
    }
}
