package com.example.mockwebservices;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.*;

public class ResponseResults {
    private final HttpStatus httpStatus;
    private final String body;

    ResponseResults(final ClientHttpResponse response) throws IOException {
        this.httpStatus = response.getStatusCode();
        final InputStream bodyInputStream = response.getBody();
        final OutputStream stringWriter = new ByteArrayOutputStream();
        IOUtils.copy(bodyInputStream, stringWriter);
        this.body = stringWriter.toString();
    }

    HttpStatus getHttpStatus() {
        return httpStatus;
    }

    String getBody() {
        return body;
    }
}