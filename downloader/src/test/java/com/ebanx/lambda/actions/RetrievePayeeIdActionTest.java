package com.ebanx.lambda.actions;

import static com.ebanx.lambda.Helper.GENERIC_DAC;
import static com.ebanx.lambda.Helper.createDownloaderRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ebanx.lambda.BaseTest;
import com.ebanx.lambda.exception.AccountStatementApiException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class RetrievePayeeIdActionTest extends BaseTest {

    @InjectMocks
    RetrievePayeeIdAction retrievePayeeIdAction;

    @Test
    void execute() {
        DownloaderResponse response = new DownloaderResponse();
        retrievePayeeIdAction.execute(createDownloaderRequest(), response);

        assertEquals(1L, response.getPayeeId());
    }

    @Test
    void executeDac() {
        DownloaderRequest request = createDownloaderRequest();
        request.setDigitalAccountId(GENERIC_DAC);
        DownloaderResponse response = new DownloaderResponse();
        retrievePayeeIdAction.execute(request, response);

        assertEquals(1L, response.getPayeeId());
    }

    @Test
    void executeException() {
        DownloaderRequest request = createDownloaderRequest();
        request.setDigitalAccountId(null);
        AccountStatementApiException exception =
                assertThrows(AccountStatementApiException.class, () -> retrievePayeeIdAction.execute(request, new DownloaderResponse()));
        assertEquals("The payee id passed is invalid", exception.getMessage());
    }
}