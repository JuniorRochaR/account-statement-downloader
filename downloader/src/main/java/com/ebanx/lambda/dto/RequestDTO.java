package com.ebanx.lambda.dto;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class RequestDTO {

    private String date;
    private String payeeId;

    public String getDate() {
        return date;
    }

    @FormParam("date")
    @PartType(MediaType.APPLICATION_JSON)
    public void setDate(String date) {
        this.date = date;
    }

    public String getPayeeId() {
        return payeeId;
    }

    @FormParam("payeeId")
    @PartType(MediaType.APPLICATION_JSON)
    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    @Override
    public String toString() {
        return "RequestDTO{" + "date='" + date + '\'' + ", payeeId='" + payeeId + '\'' + '}';
    }
}
