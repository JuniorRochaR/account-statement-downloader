package com.ebanx.lambda.actions;

public class DownloaderRequest {

    private String digitalAccountId;
    private String date;

    public String getDigitalAccountId() {
        return digitalAccountId;
    }

    public void setDigitalAccountId(String digitalAccountId) {
        this.digitalAccountId = digitalAccountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
