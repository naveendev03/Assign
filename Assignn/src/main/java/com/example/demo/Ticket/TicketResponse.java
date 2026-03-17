package com.example.demo.Ticket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {


    private Long ticketId;

    private String assignedDeveloper;

    private String developerEmail;

    private String module;
}