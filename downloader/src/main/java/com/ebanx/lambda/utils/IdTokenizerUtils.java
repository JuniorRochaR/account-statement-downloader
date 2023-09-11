package com.ebanx.lambda.utils;

import org.apache.commons.lang3.StringUtils;

public final class IdTokenizerUtils {

    private IdTokenizerUtils() {
        // Utility class :)
    }

    public static Long fromToken(String token) {

        String[] splittedToken = token.split("_");

        String tokenContent = splittedToken[1];
        String rawToken = CipherUtils.decrypt(tokenContent);

        return getIdFromToken(rawToken);
    }

    private static Long getIdFromToken(String token) {
        String cleanToken = StringUtils.trimToNull(token);
        String[] split = StringUtils.split(cleanToken, ':');
        return Long.valueOf(split[0]);
    }

}