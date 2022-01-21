package org.puprleteam.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpSender {
    private String destinationUrl;

    public HttpSender(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }
    
    /***
     * Method to connect to any Server waiting for client connection (for ex. some URL) and send to it
     * HttpData. After connection method get data from server and return it as new HttpData
     * @param httpData request as HttpData to send
     * @return httpData response from destination
     * @throws IOException
     */
    public HttpData sendRequest(HttpData httpData) throws IOException {
        String request = httpData.mergeRequest(httpData);
        URL url = new URL(destinationUrl + request);
        BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
        HttpData result = new HttpData();
        result.readData(input);
        return result;
    }

}
