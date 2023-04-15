package com.feriadedaxecommerce.repository;

import com.feriadedaxecommerce.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}

