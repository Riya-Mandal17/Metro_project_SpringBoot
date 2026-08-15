package com.metro.backend.controller;

import com.metro.backend.entity.postgres.Ticket;
import com.metro.backend.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public List<Ticket> getTcikets(){
        return ticketService.ticketDetails();
    }

    @PostMapping("/tickets")
    public Ticket newTickets(@RequestBody Ticket ticket){
         return ticketService.getNewTicket(ticket);
    }
}
