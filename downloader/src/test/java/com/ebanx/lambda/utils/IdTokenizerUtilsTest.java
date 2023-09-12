package com.ebanx.lambda.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IdTokenizerUtilsTest {

    @Test
    void fromToken() {
        assertEquals(1L, IdTokenizerUtils.fromToken("dac_C4933863FFDB84E3"));
        assertEquals(1234L, IdTokenizerUtils.fromToken("dac_D2F9F9B742545428"));
    }
}