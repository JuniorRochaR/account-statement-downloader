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
import com.ebanx.lambda.dto.ResponseDTO;

@Path("statement")
public class Controller {

    @Inject
    Orchestrator orchestrator;

    @GET
    @Path("v1/download")
    @Produces(MediaType.APPLICATION_JSON)
    public Response downloadStatement(@QueryParam("date") String date, @QueryParam("payeeId") String payeeId) {
        DownloaderRequest request = new DownloaderRequest(payeeId, date);
        DownloaderResponse response = new DownloaderResponse();
        orchestrator.run(request, response);
        return createResponse(response);
    }

    private Response createResponse(DownloaderResponse response) {
        ResponseDTO responseDTO = new ResponseDTO(response.getFileUrl());
        return Response.ok(responseDTO).build();
    }
}
