package com.metro.backend.worker;

import com.metro.backend.repository.postgres.TicketRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class CronJob {

    private final TicketRepository ticketRepository;

    public CronJob(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void refreshTickets(){
        ticketRepository.refreshTickets(LocalDateTime.now());

    }
}
