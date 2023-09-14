package com.ebanx.lambda.controllers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ebanx.lambda.actions.DownloaderRequest;
import com.ebanx.lambda.actions.DownloaderResponse;
import com.ebanx.lambda.actions.Orchestrator;

@Path("statement")
public class Controller {

    @Inject
    Orchestrator orchestrator;

    @GET
    @Path("v1/download")
    public Response downloadStatement(@QueryParam("date") String date, @QueryParam("payeeId") String payeeId) {
        DownloaderRequest request = new DownloaderRequest(payeeId, date);
        DownloaderResponse response = new DownloaderResponse();
        orchestrator.run(request, response);
        return createResponse(response);
    }

    private Response createResponse(DownloaderResponse downloaderResponse) {
        Response.ResponseBuilder response = Response.ok(downloaderResponse.getStatementCsv());
        response.header("Content-Disposition", "attachment;filename=" + "account-statement.zip");
        response.header("Content-Type", MediaType.APPLICATION_OCTET_STREAM);
        return response.build();
    }
}
