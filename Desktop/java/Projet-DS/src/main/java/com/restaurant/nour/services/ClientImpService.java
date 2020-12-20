package com.restaurant.nour.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

	//*************** l'idée est de trouver le client qui possède le maximum des tickets = client fidèle***********//
	@Override
	public String clientFidele() {
		//Client clientFidele = new Client();
		List<Client> clients= rechercheTousLesClient();
		Map<String, Integer> data = new HashMap<>();
		int nb=0;
		
		for (Client client :clients)
		{
			nb=client.getTickets().size();
			data.put(client.getName(), nb);
		}
		
		System.out.println(data);
		
		Entry<String, Integer> entry = data.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).get(); // cherhcer la valeur maximale
		 
		return("Le client le plus fidèle est (Nom du client=" + entry.getKey() + ", avec le nombre total des tickets chez nous=" + entry.getValue() + ")");
	}

	@Override
	public String clientJourReserve(int id) {
		
		//List<Client> clients  = rechercheTousLesClient();
		Client client = rechercheParId(id);
		List<LocalDate> dateReservation=new ArrayList<>();
		List<Integer> jourDeLaSemaine  = new ArrayList<Integer>();
		
			List<Ticket> tickets=client.getTickets();
			 
			   for (Ticket ticket : tickets)
			   {
				  dateReservation.add(ticket.getDate());
			   }
		
		System.out.println(dateReservation);
		 
		 for (LocalDate date : dateReservation)
		     // System.out.println(date.getDayOfWeek().getValue());
		  
		    jourDeLaSemaine.add(date.getDayOfWeek().getValue());
		 
		 Map<Integer, Long> data =count((jourDeLaSemaine).stream()); // compter le nombre des plats pour chaque plat &stocker le resultat dans un map
			
			Entry<Integer, Long> entry = data.entrySet().stream().max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue())).get(); // cherhcer la valeur maximale
			 
			return("Le jour de la semaine le plus reservé par le client "
					+id+ " est le jour " + entry.getKey()+
					"\nle jour 1 : Monday\nle jour 2 : Tuesday\nle jour 3 : Wednesday\nle jour 4 : Thursday\nle jour 5 : Friday\nle jour 6 : Saturday\n"
					+ "le jour 7 : Sunday");
	}
	
	//*********** méthode suivante qui compte le nombre d’occurrences de chaque valeur*******///

	public static <E> Map<E, Long> count(Stream<E> stream) {
	    return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
	
	
}
