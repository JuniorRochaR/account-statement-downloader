package com.ebanx.lambda.actions;

import static com.ebanx.lambda.Helper.createDownloaderRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ebanx.lambda.BaseTest;
import com.ebanx.lambda.exception.AccountStatementApiException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ConvertDateActionTest extends BaseTest {

    @InjectMocks
    ConvertDateAction convertDateAction;

    @Test
    void execute() {
        DownloaderResponse response = new DownloaderResponse();
        convertDateAction.execute(createDownloaderRequest(), response);

        assertEquals("2023/9/18/", response.getDateBucketPath());
    }

    @Test
    void executeException() {
        DownloaderRequest request = createDownloaderRequest();
        request.setDate(null);
        assertThrows(AccountStatementApiException.class, () -> convertDateAction.execute(request, new DownloaderResponse()));
    }
}