package com.ebanx.lambda;

import javax.inject.Inject;
import javax.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ebanx.lambda.actions.DownloaderRequest;
import com.ebanx.lambda.actions.DownloaderResponse;
import com.ebanx.lambda.actions.Orchestrator;

import org.jboss.logging.Logger;

@Named("handler")
public class Handler implements RequestHandler<Object, Void> {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    @Inject
    Orchestrator orchestrator;

    @Override
    public Void handleRequest(Object o, Context context) {
        DownloaderRequest request = new DownloaderRequest();
        request.setDate("2023-09-06");
        request.setDigitalAccountId("dac_D2F9F9B742545428");

        DownloaderResponse response = new DownloaderResponse();

        orchestrator.run(request, response);

        return null;
    }
}
