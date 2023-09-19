package com.ebanx.lambda.exception;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ebanx.lambda.dto.ErrorResponseDTO;
import com.ebanx.lambda.utils.ObjectMapperUtils;

import org.jboss.logging.Logger;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error(e.getMessage(), e.getCause());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
        return Response.status(INTERNAL_SERVER_ERROR)
                .entity(ObjectMapperUtils.parseToJsonString(errorResponseDTO))
                .build();
    }
}
