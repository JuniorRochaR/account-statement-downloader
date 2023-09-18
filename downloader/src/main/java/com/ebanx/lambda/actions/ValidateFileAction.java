package com.ebanx.lambda.actions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ebanx.lambda.internal.Command;
import com.ebanx.lambda.service.S3Service;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ValidateFileAction implements Command<DownloaderRequest, DownloaderResponse> {

    private static final Logger LOG = Logger.getLogger(ValidateFileAction.class);

    @Inject
    S3Service s3Service;

    @Override
    public void execute(DownloaderRequest request, DownloaderResponse response) {
        LOG.infof("Checking if file exists: %s", response.getFileName());
        s3Service.findFile(response.getFileName());
    }
}
