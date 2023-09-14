package com.ebanx.lambda.exception;

public class AccountStatementApiException extends RuntimeException {

    public AccountStatementApiException(String message) {
        super(message);
    }

    public AccountStatementApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
