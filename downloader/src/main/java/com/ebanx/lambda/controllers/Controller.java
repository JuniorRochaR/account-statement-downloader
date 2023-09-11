package com.ebanx.lambda.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public Response downloadStatement(@MultipartForm RequestDTO requestDTO) {
        DownloaderRequest request = new DownloaderRequest();
//        request.setDate("2023-09-06");
//        request.setDigitalAccountId("dac_D2F9F9B742545428");

        request.setDate(requestDTO.getDate());
        request.setDigitalAccountId(requestDTO.getPayeeId());

        DownloaderResponse response = new DownloaderResponse();

        orchestrator.run(request, response);

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
