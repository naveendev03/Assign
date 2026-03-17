package com.example.demo.Ticket;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    @Column(name = "ticket_id", nullable = false)
    private String externalTicketId;

    private Long assignedDeveloperId;

    private String module;

    private String status;
}