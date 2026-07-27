package com.metro.backend.repository.postgres;

import com.metro.backend.entity.postgres.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query("Select t from Ticket t order by t.created_at desc")
    public List<Ticket> findAllByOrderByCreatedAtDesc();

    @Modifying
    @Transactional
    @Query("update Ticket t set t.status = 'EXPIRED' where t.status = 'ACTIVE' and t.expires_at < :now ")
    public void refreshTickets(@Param("now") LocalDateTime now);


}
