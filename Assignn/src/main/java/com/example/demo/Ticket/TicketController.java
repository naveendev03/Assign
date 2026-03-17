package com.example.demo.Ticket;

import com.example.demo.Ticket.TicketRequest;
import com.example.demo.Ticket.TicketResponse;
import com.example.demo.Ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/process")
    public TicketResponse processTicket(@RequestBody TicketRequest request) {

        return ticketService.processTicket(request);
    }
}