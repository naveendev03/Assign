package com.example.demo.Ticket;

import com.example.demo.Ollama.EmbeddingService;
import com.example.demo.Ticket.TicketRequest;
import com.example.demo.Ticket.TicketResponse;
import com.example.demo.Developer.Developer;
import com.example.demo.Ticket.Ticket;
import com.example.demo.Developer.DeveloperRepository;
import com.example.demo.Ticket.TicketRepository;
import com.example.demo.Vectors.VectorStoreService;
import com.example.demo.Webhook.WebhookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final EmbeddingService embeddingService;
    private final VectorStoreService vectorStoreService;
    private final DeveloperRepository developerRepository;
    private final TicketRepository ticketRepository;
    private final WebhookClient webhookClient;

    public TicketResponse processTicket(TicketRequest request) {

        // 1 Combine title + description
        String ticketContent = request.getTitle() + " " + request.getDescription();

        // 2 Generate embedding from Ollama
        List<Double> embedding =
                embeddingService.generateEmbedding(ticketContent);

        // 3 Search module from vector database
        String module = vectorStoreService.searchModule(embedding);

        // 4 Fetch developer from Postgres
        Developer developer = developerRepository
                .findByModule(module)
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        // 5 Create ticket
        Ticket ticket = Ticket.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .assignedDeveloperId(developer.getId())
                .module(module)
                .externalTicketId(request.getTicketId())
                .status("ASSIGNED")
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        webhookClient.sendAssignment(
                "jira", // for testing.
                request.getTicketId(),
                developer.getId().toString()
        );
        // 6 Response
        return TicketResponse.builder()
                .ticketId(savedTicket.getId())
                .assignedDeveloper(developer.getName())
                .developerEmail(developer.getEmail())
                .module(module)
                .build();
    }
}