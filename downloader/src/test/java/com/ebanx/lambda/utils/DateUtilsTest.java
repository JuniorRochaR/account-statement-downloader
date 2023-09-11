package com.ebanx.lambda.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void dateToBucketPath() {
        assertEquals("2023/9/6/", DateUtils.dateToBucketPath("2023-09-06"));
    }
}