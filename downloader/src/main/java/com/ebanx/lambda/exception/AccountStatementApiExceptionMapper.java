package com.ebanx.lambda.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class AccountStatementApiExceptionMapper implements ExceptionMapper<AccountStatementApiException> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class);

    @Override
    public Response toResponse(AccountStatementApiException e) {
        LOGGER.error(e.getMessage(), e.getCause());
        ErrorResponse errorResponse = new ErrorResponse(e.getResponseStatus().getStatusCode(), e.getMessage());
        return Response.status(e.getResponseStatus())
                .entity(errorResponse)
                .build();
    }
}
