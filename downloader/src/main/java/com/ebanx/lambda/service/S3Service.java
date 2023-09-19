package com.ebanx.lambda.service;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ebanx.lambda.exception.AccountStatementApiException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.configuration.ProfileManager;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@ApplicationScoped
public class S3Service {

    @Inject
    S3Client s3Client;

    @Inject
    S3Presigner s3Presigner;

    @ConfigProperty(name = "statement.bucket.name")
    String bucketName;

    public void findFile(String keyFileName) {
        try {
            s3Client.headObject(getHeadObjectRequest(bucketName, keyFileName));
        } catch (S3Exception e) {
            if (NOT_FOUND.getStatusCode() == e.statusCode()) {
                throw new AccountStatementApiException("There is no account statement for the given parameters", e);
            }
            throw new AccountStatementApiException("Error when checking account statements", e);
        }
    }

    public String getFileUrl(String keyFileName) {
        try {
            getS3Presigner();
            PresignedGetObjectRequest presignedGetObjectRequest =
                    s3Presigner.presignGetObject(getObjectPresignRequest(getObjectRequest(bucketName, keyFileName)));
            return presignedGetObjectRequest.url().toString();
        } catch (Exception e) {
            throw new AccountStatementApiException("Error when generating account statement download URL", e);
        }
    }

    private void getS3Presigner() {
        if ("prod".equals(ProfileManager.getActiveProfile())) {
            s3Presigner = S3Presigner.builder().region(Region.US_EAST_1).build();
        }
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

    private HeadObjectRequest getHeadObjectRequest(String bucketName, String keyName) {
        return HeadObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();
    }
}
