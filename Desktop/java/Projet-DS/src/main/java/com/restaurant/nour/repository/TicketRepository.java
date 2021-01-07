package com.restaurant.nour.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.nour.entities.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
	 @Query(" select t from Ticket t " +
	            " where t.date = ?1")
	    Optional<Ticket> findTicketWithDate(LocalDate date);

}
