package com.ebanx.lambda.utils;

import static com.ebanx.lambda.Helper.GENERIC_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ebanx.lambda.dto.ErrorResponseDTO;
import com.ebanx.lambda.dto.ResponseDTO;

import org.junit.jupiter.api.Test;

class ObjectMapperUtilsTest {

    @Test
    void parseToJsonString() {
        ResponseDTO responseDTO = new ResponseDTO(GENERIC_URL);
        String json = ObjectMapperUtils.parseToJsonString(responseDTO);
        assertEquals("{\"url\":\"MY_URL\"}", json);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(123, "lorem ipsum");
        json = ObjectMapperUtils.parseToJsonString(errorResponseDTO);
        assertEquals("{\"statusCode\":123,\"errorMessage\":\"lorem ipsum\"}", json);
    }
}