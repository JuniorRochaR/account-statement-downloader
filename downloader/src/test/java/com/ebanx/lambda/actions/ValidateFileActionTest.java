package com.ebanx.lambda.actions;

import static com.ebanx.lambda.Helper.createDownloaderRequest;
import static com.ebanx.lambda.Helper.createDownloaderResponse;
import static org.mockito.Mockito.verify;

import com.ebanx.lambda.BaseTest;
import com.ebanx.lambda.service.S3Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ValidateFileActionTest extends BaseTest {

    @Mock
    S3Service s3Service;

    @InjectMocks
    ValidateFileAction validateFileAction;

    @Test
    void execute() {
        validateFileAction.execute(createDownloaderRequest(), createDownloaderResponse());
        verify(s3Service).findFile(createDownloaderResponse().getFileName());
    }
}