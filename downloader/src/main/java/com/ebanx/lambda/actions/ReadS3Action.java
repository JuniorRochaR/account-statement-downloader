package com.ebanx.lambda.actions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ebanx.lambda.internal.Command;
import com.ebanx.lambda.service.S3Service;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ReadS3Action implements Command<DownloaderRequest, DownloaderResponse> {

    private static final Logger LOG = Logger.getLogger(ReadS3Action.class);

    @Inject
    S3Service service;

    @Override
    public void execute(DownloaderRequest request, DownloaderResponse response) {
        LOG.info(response.getFileName());
        response.setFileUrl(service.getFileUrl(response.getFileName()));
        LOG.info(response.getFileUrl());
    }
}
