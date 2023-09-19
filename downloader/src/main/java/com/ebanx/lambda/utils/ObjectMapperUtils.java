package com.ebanx.lambda.utils;

import com.ebanx.lambda.exception.AccountStatementApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperUtils {

    private ObjectMapperUtils() {
        //
    }

    public static String parseToJsonString(Object message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new AccountStatementApiException("Failed to serialize response", e);
        }
    }
}
