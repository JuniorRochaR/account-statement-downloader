package com.ebanx.lambda.service;

import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@ApplicationScoped
public class S3Service {

    private static final Logger LOG = Logger.getLogger(S3Service.class);

    @Inject
    S3Client s3Client;

    @ConfigProperty(name = "statement.bucket.name")
    String bucketName;

    public InputStream getFileStream(String keyFileName) {
        LOG.debugf("GETTING FILE FROM BUCKET AND ITS STREAM - Bucket name: %s, Key: %s", bucketName, keyFileName);
        ResponseInputStream<GetObjectResponse> object = s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(keyFileName).build());
        LOG.debugf("FILE RECEIVED SUCCESSFULLY");
        return object;
    }
}
