package com.example.demo.Ticket;

import lombok.Data;

@Data
public class TicketRequest {

    private String ticketId;

    private String title;

    private String description;
}