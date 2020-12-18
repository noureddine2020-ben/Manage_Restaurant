package com.restaurant.nour.services;

import java.util.List;


import com.restaurant.nour.entities.Client;

public interface ClientService {
	
	public List<Client> rechercheTousLesClient();
	public Client rechercheParId(int id);
	public Client ajouterClient(Client c);
	public Client miseAjourClient(int id,Client client);
	public Client supprimerClient(int id);

}
