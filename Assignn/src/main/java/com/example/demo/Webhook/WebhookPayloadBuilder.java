package com.example.demo.Webhook;

import java.util.Map;

public class WebhookPayloadBuilder {

    public static String buildPayload(String template, Map<String, String> values) {

        String payload = template;

        for (Map.Entry<String, String> entry : values.entrySet()) {
            payload = payload.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return payload;
    }
}