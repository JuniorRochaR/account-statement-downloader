package com.ebanx.lambda.config;

import javax.enterprise.inject.Disposes;
import javax.ws.rs.Produces;

import io.quarkus.runtime.configuration.ProfileManager;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

public class S3PresignerConfig {

    @Produces
    public S3Presigner s3Presigner() {
        S3Presigner.Builder builder = S3Presigner.builder();
        if (!"dev".equals(ProfileManager.getActiveProfile())) {
            builder = builder.region(Region.US_EAST_1);
        }
        return builder.build();
    }

    public void close(@Disposes S3Presigner s3Presigner) {
        s3Presigner.close();
    }
}
