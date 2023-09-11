package com.ebanx.lambda.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ebanx.lambda.actions.DownloaderRequest;
import com.ebanx.lambda.actions.DownloaderResponse;
import com.ebanx.lambda.actions.Orchestrator;
import com.ebanx.lambda.dto.RequestDTO;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("statement")
public class Controller {

    @Inject
    Orchestrator orchestrator;

    @GET
    @Path("v1/download")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadStatement(@MultipartForm RequestDTO requestDTO) {
        DownloaderRequest request = new DownloaderRequest();
//        request.setDate("2023-09-06");
//        request.setDigitalAccountId("dac_D2F9F9B742545428");
        request.setDate(requestDTO.getDate());
        request.setDigitalAccountId(requestDTO.getPayeeId());

        DownloaderResponse response = new DownloaderResponse();

        orchestrator.run(request, response);

        return createResponse(response);
    }

    private Response createResponse(DownloaderResponse response) {
        Response.ResponseBuilder response2 = Response.ok(response.getStatementCsv());
        response2.header("Content-Disposition", "attachment;filename=" + "account-statement.csv");
        response2.header("Content-Type", "text/csv");
        return response2.build();
    }
}
