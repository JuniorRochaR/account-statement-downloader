package com.ebanx.lambda.exception;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

class AccountStatementApiExceptionMapperTest {

    @Test
    void toResponse() {
        AccountStatementApiException apiException = new AccountStatementApiException("erro", new Throwable("cause"));
        AccountStatementApiExceptionMapper exceptionMapper = new AccountStatementApiExceptionMapper();

        Response response = exceptionMapper.toResponse(apiException);

        assertNotNull(response);
        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("{\"statusCode\":400,\"errorMessage\":\"erro\"}", response.getEntity());
    }
}