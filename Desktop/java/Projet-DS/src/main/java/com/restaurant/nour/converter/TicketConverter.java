package com.restaurant.nour.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.restaurant.nour.dto.TicketDto;
import com.restaurant.nour.entities.Ticket;

@Component
public class TicketConverter {
	
	public TicketDto entityToDto(Ticket ticket) //methode pour convertir l'entité ticket à l'entité dto ->les champs à visualiser
	{
		TicketDto dto=new TicketDto();
		dto.setNumero(ticket.getNumero());
		dto.setNbrCouvert(ticket.getNbrCouvert());
		dto.setAddition(ticket.getAddition());
		dto.setDate(ticket.getDate());
		dto.setClient(ticket.getClient());
		dto.setTable(ticket.getTable());
		
		
		
	
		
		return dto;
	}
	
	public Ticket dtoToEntity(TicketDto ticketdto) //methode pour convertir l'entité dtoticket à l'entité ticket -> les champs à visualiser
	{
		Ticket tic=new Ticket();
		tic.setNumero(ticketdto.getNumero());
		tic.setNbrCouvert(ticketdto.getNbrCouvert());
		tic.setAddition(ticketdto.getAddition());
		tic.setDate(ticketdto.getDate());
		tic.setClient(ticketdto.getClient());
		tic.setTable(ticketdto.getTable());
		
		
		
		
		return tic;
	}
	
    
	public List<TicketDto> entityToDto(List<Ticket> tickets)
	
	{
		return tickets.stream().map(x->entityToDto(x)).collect(Collectors.toList());
	}
	
	public List<Ticket> dtoToEntity(List<TicketDto> ticketdto)
	{
		return ticketdto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
	}
	

}
