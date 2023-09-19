package com.ebanx.lambda.exception;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

class GeneralExceptionMapperTest {

    @Test
    void toResponse() {
        Exception exception = new Exception("exception");
        GeneralExceptionMapper exceptionMapper = new GeneralExceptionMapper();

        Response response = exceptionMapper.toResponse(exception);

        assertNotNull(response);
        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("{\"statusCode\":500,\"errorMessage\":\"exception\"}", response.getEntity());
    }
}