package com.microservices.challenge.sumcalculatorservice.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;

public class RequestBodyReader {

    public static String readRequestBody(HttpServletRequest request) throws IOException {
        try (InputStream inputStream = request.getInputStream()) {
            return convertInputStreamToString(inputStream);
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name()).useDelimiter(
                "\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
