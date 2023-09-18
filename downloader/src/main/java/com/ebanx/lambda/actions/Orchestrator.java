package com.ebanx.lambda.actions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ebanx.lambda.internal.Executor;

@ApplicationScoped
public class Orchestrator extends Executor<DownloaderRequest, DownloaderResponse> {

    @Inject
    RetrievePayeeIdAction retrievePayeeIdAction;

    @Inject
    ConvertDateAction convertDateAction;

    @Inject
    ValidateFileAction validateFileAction;

    @Inject
    GenerateDownloadUrlAction generateDownloadUrlAction;

    @Override
    protected void setUp() {
        add(retrievePayeeIdAction);
        add(convertDateAction);
        add(validateFileAction);
        add(generateDownloadUrlAction);
    }
}
