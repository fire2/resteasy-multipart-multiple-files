package org.acme;

import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;

import java.io.File;
import java.util.List;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class MultipleFilesClientRequest {

    @RestForm("resources")
    @PartType(APPLICATION_OCTET_STREAM)
    List<File> resources;

    public MultipleFilesClientRequest() {}

    public MultipleFilesClientRequest(List<File> resources) {
        this.resources = resources;
    }

}
