package com.restaurant.nour.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.nour.converter.ClientConverter;
import com.restaurant.nour.converter.TicketConverter;
import com.restaurant.nour.dto.TableDto;
import com.restaurant.nour.dto.TicketDto;
	
import com.restaurant.nour.entities.Ticket;
import com.restaurant.nour.services.TicketImpService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	
	
	TicketImpService service;
	TicketConverter converterTicket;
	ClientConverter clientConverter;
	
	@Autowired
	public TicketController(TicketImpService service, TicketConverter converterTicket,ClientConverter clientConverter) {
		super();
		this.service = service;
		this.converterTicket = converterTicket;
		this.clientConverter=clientConverter;
	}
	
	@GetMapping
	public List<TicketDto> rechercheTicket()
	{
		List<Ticket> tickets=service.rechercheTousLesTicket();
		return converterTicket.entityToDto(tickets);
	}
	
	@GetMapping("/{num}")
	public TicketDto rechercheTicketParNum(@PathVariable(name = "num") int num)
	{
		Ticket ticket=service.rechercheParNum(num);
		return converterTicket.entityToDto(ticket);
	}
	
	@PostMapping
	public TicketDto addTicket(@RequestBody @Valid TicketDto tickDto)
	{   
		Ticket ticket= converterTicket.dtoToEntity(tickDto);
	   
		service.ajouterTicket(ticket);
		return converterTicket.entityToDto(ticket);
	}
	
	@DeleteMapping("/{num}")
	public TicketDto deleteTicket(@PathVariable(name="num") int num,@RequestBody TicketDto requestTicket)
	{
		Ticket ticket = converterTicket.dtoToEntity(requestTicket);
		ticket=service.supprimerTicket(num);
		return converterTicket.entityToDto(ticket);
	}
	
	@PutMapping("/{num}")
	public TicketDto updateTicket(@PathVariable(name="num") int num,@RequestBody @Valid TicketDto requestTicket)
	{
		Ticket ticket = converterTicket.dtoToEntity(requestTicket);
		 
		ticket=service.miseAjourTicket(num, ticket);
		
		return converterTicket.entityToDto(ticket);
			
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
