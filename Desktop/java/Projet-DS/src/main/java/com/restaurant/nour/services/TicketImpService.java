package com.restaurant.nour.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.nour.converter.TicketConverter;
import com.restaurant.nour.dto.TicketDto;
import com.restaurant.nour.entities.Client;
import com.restaurant.nour.entities.TablePlace;
import com.restaurant.nour.entities.Ticket;
import com.restaurant.nour.repository.ClientRepository;
import com.restaurant.nour.repository.TicketRepository;

@Service
public class TicketImpService implements TicketService {
	
	TicketRepository reposTicket;
	ClientImpService serviceClient;
	TableImpService  tableservice;
	TicketConverter  converterTickt;
	
   
    
    @Autowired
	public TicketImpService(TicketRepository reposTicket, ClientImpService serviceClient, TableImpService tableservice,
			TicketConverter converterTickt) {
		super();
		this.reposTicket = reposTicket;
		this.serviceClient = serviceClient;
		this.tableservice = tableservice;
		this.converterTickt = converterTickt;
	}

	@Override
	public List<Ticket> rechercheTousLesTicket() {
		  return reposTicket.findAll();
		
	}

	@Override
	public Ticket rechercheParNum(int num) {
		
		 Optional<Ticket> ticket=reposTicket.findById(num);
		 Ticket tick = null;
		 tick=ticket.orElseThrow(()->new NoSuchElementException("Attention le ticket avec le numéro "+num+ " n'existe pas"));
		 return tick;
		
	}

	@Override
	public Ticket ajouterTicket(Ticket tic) {
		 TablePlace table=tic.getTable(); 
		 Client client = tic.getClient();
		 serviceClient.ajouterClient(client);
		 tableservice.ajouterTable(table);
		 return  reposTicket.save(tic);
		
	}

	@Override
	public Ticket miseAjourTicket(int num, Ticket requestTicket) {
		
		Ticket oldTick=this.rechercheParNum(num);
		
		if(oldTick.getAddition()!=0)
			oldTick.setAddition(requestTicket.getAddition());
		if(oldTick.getNbrCouvert()!=0)
			oldTick.setNbrCouvert(requestTicket.getNbrCouvert());
		if (oldTick.getDate()!=null)
			oldTick.setDate(requestTicket.getDate());
		
		//update Client
		Client newClient = requestTicket.getClient();
		Client oldClient = requestTicket.getClient();
		
		if(oldClient==null)
		    oldTick.setClient(requestTicket.getClient());
		else {
			if(newClient!=null)
			{
				  oldClient.setName(newClient.getName());
			      oldClient.setPrenom(newClient.getPrenom());
			      
			      // test sur les attributs facultatifs du client
			      if(oldClient.getCourriel()!=null)
			    	  oldClient.setCourriel(newClient.getCourriel());
			      if(oldClient.getTelephone()!=null)
			    	  oldClient.setTelephone(newClient.getTelephone());
			}	
		}
		
		//update Table
		  TablePlace oldTable = requestTicket.getTable();
		  TablePlace newTable = requestTicket.getTable();
		  
		  if(oldTable==null)
			  oldTick.setTable(requestTicket.getTable());
		  else
		  {
			  if(newTable!=null)
			  {
				  oldTable.setNbrCouvert(newTable.getNbrCouvert());
				  oldTable.setSupplement(newTable.getSupplement());
				  oldTable.setType(newTable.getType());
			  }
		  }
		  
		  
		return reposTicket.save(oldTick);
	}

	@Override
	public Ticket supprimerTicket(int num) {
	Ticket oldTicket = this.rechercheParNum(num);
	   reposTicket.delete(oldTicket);
	   return oldTicket;
	}
	

}
