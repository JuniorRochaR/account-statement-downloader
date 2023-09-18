package com.ebanx.lambda.utils;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ebanx.lambda.exception.AccountStatementApiException;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

    private static final String UNEXPECTED_PATTERN = "Date parameter does not have the expected pattern (yyyy-MM-dd)";

    @Test
    void dateToBucketPath() {
        assertEquals("2023/9/6/", DateUtils.dateToBucketPath("2023-09-06"));
        assertEquals("1999/12/31/", DateUtils.dateToBucketPath("1999-12-31"));
        assertEquals("2023/11/1/", DateUtils.dateToBucketPath("2023-11-01"));
        assertEquals("2023/1/22/", DateUtils.dateToBucketPath("2023-01-22"));

        AccountStatementApiException exception;
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath(null));
        assertEquals("Date param cannot be null", exception.getMessage());

        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("2023"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("2023-01"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("2023-01-1"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("2023-2-2"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("12-12-2023"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("12-2023"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath("1-12-2023"));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
        exception = assertThrows(AccountStatementApiException.class, () -> DateUtils.dateToBucketPath(EMPTY));
        assertEquals(UNEXPECTED_PATTERN, exception.getMessage());
    }
}