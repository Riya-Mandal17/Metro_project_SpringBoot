package com.metro.backend.service;

import com.metro.backend.entity.postgres.Ticket;
import com.metro.backend.repository.postgres.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }
    public List<Ticket> ticketDetails() {
        return ticketRepository.findAllByOrderByCreatedAtDesc();

    }

    public Ticket getNewTicket(Ticket ticket) {
        LocalDateTime currentTime = LocalDateTime.now();
        ticket.setCreated_at(currentTime);
        ticket.setExpires_at(currentTime.plusMinutes(30));

        String ticketNum = "KMETRO-"+ UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();

        ticket.setTicket_number(ticketNum);

        return ticketRepository.save(ticket);
    }
}
