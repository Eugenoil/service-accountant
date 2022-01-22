package org.puprleteam.dto;

import com.sun.net.httpserver.Headers;
import lombok.Value;
import org.puprleteam.api.StatusCode;

@Value
public class ResponseEntity <T>{

    private final T body;
    private final Headers headers;
    private final StatusCode statusCode;
}
