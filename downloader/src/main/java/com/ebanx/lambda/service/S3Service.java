package com.ebanx.lambda.service;

import java.io.InputStream;
import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

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

    public String getFileUrl(String keyFileName) {
        S3Presigner s3Presigner = getS3Presigner();
        LOG.debugf("GETTING FILE FROM BUCKET AND ITS STREAM - Bucket name: %s, Key: %s", bucketName, keyFileName);
        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest(getObjectRequest(bucketName, keyFileName)));
        LOG.debugf("FILE RECEIVED SUCCESSFULLY");
        return presignedGetObjectRequest.url().toString();
    }

    private S3Presigner getS3Presigner() {
        return S3Presigner.builder().region(Region.US_EAST_1).build();
    }

    private GetObjectRequest getObjectRequest(String bucketName, String keyName) {
        return GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();
    }

    private GetObjectPresignRequest getObjectPresignRequest(GetObjectRequest getObjectRequest) {
        return GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();
    }
}
