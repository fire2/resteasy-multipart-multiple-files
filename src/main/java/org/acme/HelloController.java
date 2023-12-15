package org.acme;

import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static jakarta.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.io.File;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/hello")
public class HelloController {

    @RestClient
    RestApiClient client;

    @POST
    @Consumes(MULTIPART_FORM_DATA)
    @Produces(TEXT_PLAIN)
    public String receiveMultiple(MultipleFilesRequest input) {

        StringBuilder sb = new StringBuilder();
        input.resources.forEach(file -> sb.append(file.fileName())
            .append(": ")
            .append(file.size())
            .append("; ")
        );

        return sb.toString();
    }

    @POST
    @Path("/single")
    @Consumes(MULTIPART_FORM_DATA)
    @Produces(TEXT_PLAIN)
    public String receiveSingle(@RestForm("resources") @PartType(APPLICATION_OCTET_STREAM) FileUpload file) {

        StringBuilder sb = new StringBuilder();
        sb.append(file.fileName())
            .append(": ")
            .append(file.size())
            .append("; ");

        return sb.toString();
    }

    @GET
    @Produces(TEXT_PLAIN)
    public String sendList() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("img1.jpg").getFile());
        MultipleFilesClientRequest request = new MultipleFilesClientRequest(List.of(file));

        return client.sendMultipleFiles(request);
    }

    @GET
    @Path("/single")
    @Produces(TEXT_PLAIN)
    public String sendSingle() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("img1.jpg").getFile());

        return client.sendSingleFile(file);
    }
}
