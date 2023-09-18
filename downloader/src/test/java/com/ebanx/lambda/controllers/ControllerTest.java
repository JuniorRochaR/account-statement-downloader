package com.ebanx.lambda.controllers;

import static com.ebanx.lambda.Helper.GENERIC_DATE;
import static com.ebanx.lambda.Helper.PAYEE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.Response;

import com.ebanx.lambda.BaseTest;
import com.ebanx.lambda.actions.DownloaderRequest;
import com.ebanx.lambda.actions.DownloaderResponse;
import com.ebanx.lambda.actions.Orchestrator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ControllerTest extends BaseTest {

    @Mock
    Orchestrator orchestrator;

    @InjectMocks
    Controller controller;

    @Test
    void downloadStatement() {
        Response response = controller.downloadStatement(GENERIC_DATE, PAYEE_ID);
        verify(orchestrator).run(any(DownloaderRequest.class), any(DownloaderResponse.class));

        assertEquals(200, response.getStatus());
        assertEquals("ResponseDTO{url='null'}", response.getEntity().toString());
    }
}