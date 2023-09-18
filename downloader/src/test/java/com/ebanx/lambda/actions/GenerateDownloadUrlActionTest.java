package com.ebanx.lambda.actions;

import static com.ebanx.lambda.Helper.GENERIC_URL;
import static com.ebanx.lambda.Helper.createDownloaderRequest;
import static com.ebanx.lambda.Helper.createDownloaderResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ebanx.lambda.BaseTest;
import com.ebanx.lambda.service.S3Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GenerateDownloadUrlActionTest extends BaseTest {

    @Mock
    S3Service s3Service;

    @InjectMocks
    GenerateDownloadUrlAction generateDownloadUrlAction;

    @Test
    void execute() {
        DownloaderResponse response = createDownloaderResponse();
        when(s3Service.getFileUrl(response.getFileName())).thenReturn(GENERIC_URL);

        generateDownloadUrlAction.execute(createDownloaderRequest(), response);
        assertEquals(GENERIC_URL, response.getFileUrl());
    }
}