package org.puprleteam.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.puprleteam.domain.NewTracking;
import org.puprleteam.dto.RegistrationRequest;
import org.puprleteam.dto.RegistrationResponse;
import org.puprleteam.dto.ResponseEntity;
import org.puprleteam.errors.ApplicationExceptions;
import org.puprleteam.errors.GlobalExceptionHandler;
import org.puprleteam.interfaces.TrackingService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class RegistrationHandler extends Handler{

    private final TrackingService trackingService;

    public RegistrationHandler(ObjectMapper objectMapper,
                               GlobalExceptionHandler exceptionHandler,
                               TrackingService trackingService) {
        super(objectMapper, exceptionHandler);
        this.trackingService = trackingService;
    }

    @Override
    protected void execute(HttpExchange exchange) throws IOException {
        byte[] response;
        if ("POST".equals(exchange.getRequestMethod())) {
            ResponseEntity e = doPost(exchange.getRequestBody());
            exchange.getResponseHeaders().putAll(e.getHeaders());
            exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
            response = super.writeResponse(e.getBody());
        } else {
            throw ApplicationExceptions.methodNotAllowed(
                    "Method " + exchange.getRequestMethod() +
                            " is not allowed for " + exchange.getRequestURI()).get();
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }

    private ResponseEntity<RegistrationResponse> doPost(InputStream is) {
        RegistrationRequest registerRequest = super.readRequest(is, RegistrationRequest.class);

        NewTracking tracking = NewTracking.builder()
                .task_name(registerRequest.getTask_name())
                .user_id(registerRequest.getUser_id())
                .status(registerRequest.getStatus())
                .build();

        Serializable trackingId = trackingService.create(tracking);

        RegistrationResponse response = new RegistrationResponse((Long) trackingId);

        return new ResponseEntity<>(response,
                getHeaders(org.puprleteam.api.Constants.CONTENT_TYPE,
                        Constants.APPLICATION_JSON), StatusCode.OK);
    }
}
