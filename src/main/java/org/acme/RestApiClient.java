package org.acme;

import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static jakarta.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.io.File;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

@RegisterRestClient(configKey = "extensions-api")
public interface RestApiClient {

    @POST
    @Path("/")
    @Consumes(MULTIPART_FORM_DATA)
    @Produces(TEXT_PLAIN)
    String sendMultipleFiles(MultipleFilesClientRequest form);

    @POST
    @Path("/single")
    @Consumes(MULTIPART_FORM_DATA)
    @Produces(TEXT_PLAIN)
    String sendSingleFile(@RestForm("resources") @PartType(APPLICATION_OCTET_STREAM) File file);

}
