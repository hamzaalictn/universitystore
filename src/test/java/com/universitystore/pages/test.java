package com.universitystore.pages;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.util.*;


public class test {
    public static void main(String[] args) throws JsonProcessingException {
        List<String> logFile = Arrays.asList(
                "2023-04-01 10:00:00;/api/users;1.23",
                "2023-04-01 10:01:00;/api/products;0.45",
                "2023-04-01 10:02:00;/api/users;2.12",
                "2023-04-01 10:03:00;/api/orders;1.87",
                "2023-04-01 10:04:00;/api/users;0.98",
                "2023-04-01 10:05:00;/api/products;1.33"
        );

        Map<String, Double> responseTimeSum = new HashMap<>();
        Map<String, Integer> requestCount = new HashMap<>();

        for (String logEntry : logFile) {
            String[] parts = logEntry.split(";");
            String endpoint = parts[1];
            double responseTime = Double.parseDouble(parts[2]);

            responseTimeSum.put(endpoint, responseTimeSum.getOrDefault(endpoint, 0.0) + responseTime);
            requestCount.put(endpoint, requestCount.getOrDefault(endpoint, 0) + 1);
        }

        String slowestEndpoint = "";
        double highestAverageResponseTime = 0;

        for (Map.Entry<String, Double> entry : responseTimeSum.entrySet()) {
            String endpoint = entry.getKey();
            double averageResponseTime = entry.getValue() / requestCount.get(endpoint);

            if (averageResponseTime > highestAverageResponseTime) {
                slowestEndpoint = endpoint;
                highestAverageResponseTime = averageResponseTime;
            }
        }

        System.out.println(slowestEndpoint);

    }
}
