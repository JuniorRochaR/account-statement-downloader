package com.ebanx.lambda;

import com.ebanx.lambda.actions.DownloaderRequest;
import com.ebanx.lambda.actions.DownloaderResponse;

public class Helper {

    public static final String GENERIC_DATE = "2023-09-18";
    public static final String PAYEE_ID = "1";
    public static final String GENERIC_DAC = "dac_C4933863FFDB84E3";
    public static final String GENERIC_URL = "MY_URL";

    public static DownloaderRequest createDownloaderRequest() {
        return new DownloaderRequest(PAYEE_ID, GENERIC_DATE);
    }

    public static DownloaderResponse createDownloaderResponse() {
        DownloaderResponse response = new DownloaderResponse();
        response.setPayeeId(1L);
        response.setFileUrl("2023/9/18/");
        return response;
    }
}
