package com.ebanx.lambda.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ebanx.lambda.dto.ErrorResponseDTO;
import com.ebanx.lambda.utils.ObjectMapperUtils;

import org.jboss.logging.Logger;

@Provider
public class AccountStatementApiExceptionMapper implements ExceptionMapper<AccountStatementApiException> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class);

    @Override
    public Response toResponse(AccountStatementApiException e) {
        LOGGER.error(e.getMessage(), e.getCause());
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getResponseStatus().getStatusCode(), e.getMessage());
        return Response.status(e.getResponseStatus())
                .entity(ObjectMapperUtils.parseToJsonString(errorResponseDTO))
                .build();
    }
}
