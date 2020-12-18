package com.restaurant.nour.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.nour.dto.ClientDto;
import com.restaurant.nour.entities.Client;
import com.restaurant.nour.entities.Ticket;
import com.restaurant.nour.repository.ClientRepository;
import com.restaurant.nour.repository.TicketRepository;

@Service
public class ClientImpService implements ClientService {
	
	  ClientRepository repoClient;
	  TicketRepository repoTicket;
	  
    
    @Autowired
	public ClientImpService(ClientRepository repoClient, TicketRepository repoTicket) {
		super();
		this.repoClient = repoClient;
		this.repoTicket = repoTicket;
	}

	@Override
	public List<Client> rechercheTousLesClient() {
		
		return repoClient.findAll();
	}

	@Override
	public Client rechercheParId(int id) {
		
		 Optional<Client> opt= repoClient.findById(id);
		 Client client = null;
		 
		 client  = opt.orElseThrow(()-> new NoSuchElementException("Attention le client avec l'id "+id+ " n'existe pas"));
		return client;
	}

	@Override
	public Client ajouterClient(Client clientRequest) {
		
		
		Client clientInBase = repoClient.save(clientRequest); // save client
		
		//List<Ticket> tickets= clientRequest.getTickets(); // save ticket we have relation oneToMany
		
		//tickets.forEach(ticket -> ticket.setClient(clientInBase));
		 
		//repoTicket.saveAll(tickets);
	
		return clientInBase;
	}

	@Override
	public Client miseAjourClient(int id,Client newClient) {
		   
	    Client oldClient=this.rechercheParId(id);
	        oldClient.setName(newClient.getName()); // ici pas de verification car le champ nom est obligatoire
	        oldClient.setPrenom(newClient.getPrenom());// ici pas de verification car le champ prenom est obligatoire
	        
	        if (oldClient.getDateDeNaissance()!=null)
	           
	        	  oldClient.setDateDeNaissance(newClient.getDateDeNaissance());
	           
	         if(oldClient.getTelephone()!=null)
	        	 
	             oldClient.setTelephone(newClient.getTelephone());
             if(oldClient.getCourriel()!=null)
	        	 
	             oldClient.setCourriel(newClient.getCourriel());
	        
		    return repoClient.save(oldClient);
	}

	@Override
	public Client supprimerClient(int id) {
		 Client oldClient= this.rechercheParId(id);
		 repoClient.deleteById(id);
		 return oldClient;
	}

}
