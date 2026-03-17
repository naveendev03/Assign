package com.example.demo.Webhook;

import com.example.demo.Webhook.WebhookConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebhookConfigRepository extends JpaRepository<WebhookConfig, Long> {

    Optional<WebhookConfig> findByPlatformName(String platformName);
}
