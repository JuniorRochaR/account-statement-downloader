package com.ebanx.lambda.utils;

import com.ebanx.lambda.exception.AccountStatementApiException;

public class DateUtils {

    private static final String BACKSLASH = "/";

    public static String dateToBucketPath(String date) {
        if (date == null) {
            throw new AccountStatementApiException("Date param cannot be null");
        }
        try {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));

            return year + BACKSLASH + month + BACKSLASH + day + BACKSLASH;
        } catch (Exception e) {
            throw new AccountStatementApiException("Date parameter does not have the expected pattern (yyyy-MM-dd)", e);
        }
    }
}
