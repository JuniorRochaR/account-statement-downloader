package com.ebanx.lambda.actions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ebanx.lambda.internal.Command;
import com.ebanx.lambda.service.S3Service;

import org.jboss.logging.Logger;

@ApplicationScoped
public class GenerateDownloadUrlAction implements Command<DownloaderRequest, DownloaderResponse> {

    private static final Logger LOG = Logger.getLogger(GenerateDownloadUrlAction.class);

    @Inject
    S3Service s3Service;

    @Override
    public void execute(DownloaderRequest request, DownloaderResponse response) {
        response.setFileUrl(s3Service.getFileUrl(response.getFileName()));
        LOG.infof("Download URL generated: %s", response.getFileUrl());
    }
}
