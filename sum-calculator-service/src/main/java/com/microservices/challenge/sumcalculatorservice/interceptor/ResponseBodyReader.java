package com.microservices.challenge.sumcalculatorservice.interceptor;

import java.io.UnsupportedEncodingException;

public class ResponseBodyReader {

    public static String getResponseBodyAsString(byte[] responseBody) {
        try {
            return new String(responseBody, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Handle the exception or log it
            return "Error reading response body";
        }
    }
}
