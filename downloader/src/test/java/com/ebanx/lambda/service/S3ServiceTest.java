package com.ebanx.lambda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.ebanx.lambda.BaseTest;
import com.ebanx.lambda.exception.AccountStatementApiException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.http.SdkHttpRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

class S3ServiceTest extends BaseTest {

    @Mock
    S3Client s3Client;

    @Mock
    S3Presigner s3Presigner;

    @InjectMocks
    S3Service s3Service;

    @Test
    void findFile() {
        s3Service.findFile(anyString());
        verify(s3Client).headObject(any(HeadObjectRequest.class));
    }

    @Test
    void findFileNotFound() {
        when(s3Client.headObject(any(HeadObjectRequest.class))).thenThrow(createS3Exception(404));

        AccountStatementApiException exception = assertThrows(AccountStatementApiException.class, () -> s3Service.findFile(anyString()));
        assertEquals("There is no account statement for the given parameters", exception.getMessage());
    }

    @Test
    void findFileClientException() {
        when(s3Client.headObject(any(HeadObjectRequest.class))).thenThrow(createS3Exception(500));

        AccountStatementApiException exception = assertThrows(AccountStatementApiException.class, () -> s3Service.findFile(anyString()));
        assertEquals("Error when checking account statements", exception.getMessage());
    }

//    @Test
//    void getFileUrl() {
//        when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class))).thenReturn(createPresignedGetObjectRequest());
//        assertEquals("http://host.docker.internal:4566", s3Service.getFileUrl(anyString()));
//    }
//
//    @Test
//    void getFileUrlException() {
//        when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class))).thenThrow(createS3Exception(500));
//
//        AccountStatementApiException exception = assertThrows(AccountStatementApiException.class, () -> s3Service.getFileUrl(anyString()));
//        assertEquals("Error when generating account statement download URL", exception.getMessage());
//    }

    private AwsServiceException createS3Exception(int statusCode) {
        return S3Exception.builder().statusCode(statusCode).build();
    }

//    private PresignedGetObjectRequest createPresignedGetObjectRequest() {
//        return PresignedGetObjectRequest.builder()
//                .expiration(Instant.parse("2023-09-18T16:12:59.12Z"))
//                .isBrowserExecutable(true)
//                .signedHeaders(Map.of("header", List.of("1")))
//                .httpRequest(
//                        SdkHttpRequest.builder()
//                                .protocol("http")
//                                .host("host.docker.internal:4566")
//                                .method(SdkHttpMethod.GET)
//                                .build())
//                .build();
//    }
}