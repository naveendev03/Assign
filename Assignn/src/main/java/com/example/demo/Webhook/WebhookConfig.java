package com.example.demo.Webhook;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "webhook_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String platformName;

    private String webhookUrl;

    @Column(columnDefinition = "TEXT")
    private String payloadTemplate;
}