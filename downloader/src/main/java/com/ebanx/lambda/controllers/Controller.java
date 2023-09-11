package com.ebanx.lambda.controllers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadStatement(@QueryParam("date") String date, @QueryParam("payeeId") String payeeId) {
//        date = "2023-09-06";
//        payeeId = "dac_D2F9F9B742545428";
        DownloaderRequest request = new DownloaderRequest(payeeId, date);
        DownloaderResponse response = new DownloaderResponse();
        orchestrator.run(request, response);
        return createResponse(response);
    }

    private Response createResponse(DownloaderResponse downloaderResponse) {
        Response.ResponseBuilder response = Response.ok(downloaderResponse.getStatementCsv());
        response.header("Content-Disposition", "attachment;filename=" + "account-statement.csv");
        response.header("Content-Type", "text/csv");
        return response.build();
    }
}
