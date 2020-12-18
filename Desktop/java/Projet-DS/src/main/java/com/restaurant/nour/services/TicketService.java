package com.restaurant.nour.services;

import java.util.List;


import com.restaurant.nour.entities.Ticket;

public interface TicketService {
	
	public List<Ticket> rechercheTousLesTicket();
	public Ticket rechercheParNum(int num);
	public Ticket ajouterTicket(Ticket tic);
	public Ticket miseAjourTicket(int num,Ticket tic);
	public Ticket supprimerTicket(int num);

}
