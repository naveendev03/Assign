package com.example.demo.Webhook;


import com.example.demo.Webhook.WebhookConfig;
import com.example.demo.Webhook.WebhookConfigRepository;
import com.example.demo.Webhook.WebhookPayloadBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WebhookClient {

    private final WebhookConfigRepository webhookConfigRepository;
    private final RestTemplate restTemplate;

    public void sendAssignment(String platform, String ticketId, String userId) {

        WebhookConfig config = webhookConfigRepository
                .findByPlatformName(platform)
                .orElseThrow(() -> new RuntimeException("Webhook config not found"));

        String payload = WebhookPayloadBuilder.buildPayload(
                config.getPayloadTemplate(),
                Map.of(
                        "ticketId", ticketId,
                        "userId", userId
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        restTemplate.postForObject(
                config.getWebhookUrl(),
                entity,
                String.class
        );
    }
}