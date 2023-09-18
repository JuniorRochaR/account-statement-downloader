package com.ebanx.lambda.exception;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.core.Response;

public class AccountStatementApiException extends RuntimeException {

    private final Response.Status responseStatus = BAD_REQUEST;

    public AccountStatementApiException(String message) {
        super(message);
    }

    public AccountStatementApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public Response.Status getResponseStatus() {
        return responseStatus;
    }
}
