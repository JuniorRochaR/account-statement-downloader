package com.ebanx.lambda.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error(e.getMessage(), e.getCause());
        ErrorResponse errorResponse = new ErrorResponse("1", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}
