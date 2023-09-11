package com.ebanx.lambda;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ApiAuthorizerHandler implements RequestHandler<TokenAuthorizerContext, HashMap<String, Object>> {

    private static final String defaultToken = "38jg91bAOKdv.masd910ko03.001@oa!";

    @Override
    public HashMap<String, Object> handleRequest(TokenAuthorizerContext input, Context context) {
        Boolean isTokenAuthorized = isAuthorized(input.getAuthorizationToken());
        return buildPolicy(isTokenAuthorized, input.getMethodArn());
    }

    public Boolean isAuthorized(String token) {
        return Objects.nonNull(token) && token.trim().length() > 0 && defaultToken.equals(token);
    }

    public HashMap<String, Object> buildPolicy(Boolean isAuthorized, String targetResourceArn) {
        final String DENY = "Deny";
        final String ALLOW = "Allow";

        HashMap<String, Object> hmStatements = new HashMap<>();
        hmStatements.put("Action", "execute-api:Invoke");
        hmStatements.put("Effect", isAuthorized ? ALLOW : DENY);
        hmStatements.put("Resource", targetResourceArn);

        HashMap<String, Object> hmPolicyDocument = new HashMap<>();
        hmPolicyDocument.put("Version", "2012-10-17");
        hmPolicyDocument.put("Statement", List.of(hmStatements));

        HashMap<String, Object> hmPrincipal = new HashMap<>();
        hmPrincipal.put("principalId", "ApiUser");
        hmPrincipal.put("policyDocument", hmPolicyDocument);

        return hmPrincipal;
    }
}