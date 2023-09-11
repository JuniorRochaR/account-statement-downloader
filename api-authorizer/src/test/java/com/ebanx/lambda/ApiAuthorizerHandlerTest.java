package com.ebanx.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ApiAuthorizerHandlerTest {

    private static final String EXPECTED_DENY_POLICY =
    "{policyDocument={Version=2012-10-17, Statement=[{Action=execute-api:Invoke, " +
            "Resource=arn:aws:execute-api:us-east-1:898401013431:yxhanz3wi5/ESTestInvoke-stage/GET/, Effect=Deny}]}, principalId=ApiUser}";

    private static final String EXPECTED_ALLOW_POLICY =
            "{policyDocument={Version=2012-10-17, Statement=[{Action=execute-api:Invoke, " +
                    "Resource=arn:aws:execute-api:us-east-1:898401013431:yxhanz3wi5/ESTestInvoke-stage/GET/, Effect=Allow}]}, principalId=ApiUser}";

    @Test
    void buildDenyPolicy() {
        String generatedPolicy = apiAuthorizerHandler.buildPolicy(false, "arn:aws:execute-api:us-east-1:898401013431:yxhanz3wi5/ESTestInvoke-stage" +
                "/GET/").toString();

        assertEquals(EXPECTED_DENY_POLICY, generatedPolicy);
    }

    @Test
    void buildAllowPolicy() {
        String generatedPolicy = apiAuthorizerHandler.buildPolicy(true, "arn:aws:execute-api:us-east-1:898401013431:yxhanz3wi5/ESTestInvoke-stage" +
                "/GET/").toString();

        assertEquals(EXPECTED_ALLOW_POLICY, generatedPolicy);
    }

    ApiAuthorizerHandler apiAuthorizerHandler = new ApiAuthorizerHandler();
}