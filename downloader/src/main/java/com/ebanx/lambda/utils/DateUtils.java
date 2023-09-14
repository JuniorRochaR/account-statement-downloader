package com.ebanx.lambda.utils;

import com.ebanx.lambda.exception.AccountStatementApiException;

public class DateUtils {

    private static final String BACKSLASH = "/";

    public static String dateToBucketPath(String date) {
        if (date == null) {
            throw new AccountStatementApiException("date param cannot be null");
        }
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        return year + BACKSLASH + month + BACKSLASH + day + BACKSLASH;
    }
}
