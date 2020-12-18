package com.restaurant.nour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.nour.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
