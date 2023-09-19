package com.ebanx.lambda.utils;

import static com.ebanx.lambda.Helper.GENERIC_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ebanx.lambda.dto.ResponseDTO;
import com.ebanx.lambda.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

class ObjectMapperUtilsTest {

    @Test
    void parseToJsonString() {
        ResponseDTO responseDTO = new ResponseDTO(GENERIC_URL);
        String json = ObjectMapperUtils.parseToJsonString(responseDTO);
        assertEquals("{\"url\":\"MY_URL\"}", json);

        ErrorResponse errorResponse = new ErrorResponse(123, "lorem ipsum");
        json = ObjectMapperUtils.parseToJsonString(errorResponse);
        assertEquals("{\"statusCode\":123,\"errorMessage\":\"lorem ipsum\"}", json);
    }
}