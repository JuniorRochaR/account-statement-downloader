package com.ebanx.lambda.actions;

import static com.ebanx.lambda.Helper.createDownloaderRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import com.ebanx.lambda.BaseTest;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class OrchestratorTest extends BaseTest {

    @Mock
    RetrievePayeeIdAction retrievePayeeIdAction;

    @Mock
    ConvertDateAction convertDateAction;

    @Mock
    ValidateFileAction validateFileAction;

    @Mock
    GenerateDownloadUrlAction generateDownloadUrlAction;

    @InjectMocks
    Orchestrator orchestrator;

    @Test
    void setUp() {
        InOrder inOrder =
                Mockito.inOrder(retrievePayeeIdAction, convertDateAction, validateFileAction, generateDownloadUrlAction);

        orchestrator.setUp();
        orchestrator.run(createDownloaderRequest(), new DownloaderResponse());

        inOrder.verify(retrievePayeeIdAction, times(1)).execute(any(DownloaderRequest.class), any(DownloaderResponse.class));
        inOrder.verify(convertDateAction, times(1)).execute(any(DownloaderRequest.class), any(DownloaderResponse.class));
        inOrder.verify(validateFileAction, times(1)).execute(any(DownloaderRequest.class), any(DownloaderResponse.class));
        inOrder.verify(generateDownloadUrlAction, times(1)).execute(any(DownloaderRequest.class), any(DownloaderResponse.class));
    }
}