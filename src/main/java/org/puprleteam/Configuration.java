package org.puprleteam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.puprleteam.errors.GlobalExceptionHandler;
import org.puprleteam.interfaces.TrackingService;
import org.puprleteam.service.TrackingServiceImpl;

class Configuration {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
     private static final TrackingService TRACKING_SERVICE = new TrackingServiceImpl();
    private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);

    static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    static TrackingService getTrackingService() {
        return TRACKING_SERVICE;
    }

    public static GlobalExceptionHandler getErrorHandler() {
        return GLOBAL_ERROR_HANDLER;
    }
}
