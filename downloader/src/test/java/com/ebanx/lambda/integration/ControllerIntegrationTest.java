package com.ebanx.lambda.integration;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.oidc.server.OidcWiremockTestResource;
import io.restassured.http.ContentType;

@Tag("integration")
@QuarkusTest
@QuarkusTestResource(OidcWiremockTestResource.class)
public class ControllerIntegrationTest {

    private static final String ENDPOINT = "/statement/v1/download";

    @Test
    void success() {
        given().contentType(ContentType.JSON)
                .when().get(ENDPOINT.concat("?payeeId=1234&date=2023-09-06"))
                .then().statusCode(OK.getStatusCode())
                .body("url", containsString("http://localhost:4566/ebanx.sta/2023/9/6/1234.zip"));
    }

    @Test
    void fileNotFound() {
        given().contentType(ContentType.JSON)
                .when().get(ENDPOINT.concat("?payeeId=1234&date=2023-09-07"))
                .then().statusCode(BAD_REQUEST.getStatusCode())
                .body("errorMessage", is("There is no account statement for the given parameters"));
    }

    @Test
    void nullDateParam() {
        given().contentType(ContentType.JSON)
                .when().get(ENDPOINT.concat("?payeeId=1"))
                .then().statusCode(BAD_REQUEST.getStatusCode())
                .body("errorMessage", is("Date param cannot be null"));
    }

    @Test
    void wrongDateFormat() {
        given().contentType(ContentType.JSON)
                .when().get(ENDPOINT.concat("?payeeId=1&date=19-09-2023"))
                .then().statusCode(BAD_REQUEST.getStatusCode())
                .body("errorMessage", is("Date parameter does not have the expected pattern (yyyy-MM-dd)"));
    }

    @Test
    void wrongPayeeId() {
        given().contentType(ContentType.JSON)
                .when().get(ENDPOINT)
                .then().statusCode(BAD_REQUEST.getStatusCode())
                .body("errorMessage", is("The payee id passed is invalid"));
    }
}
