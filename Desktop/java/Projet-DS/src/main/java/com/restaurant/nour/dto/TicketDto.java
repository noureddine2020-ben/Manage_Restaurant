package com.restaurant.nour.dto;

import java.time.LocalDate;

import com.restaurant.nour.entities.Client;
import com.restaurant.nour.entities.TablePlace;

import lombok.Data;

@Data
public class TicketDto {
	
	private int numero;
	
    private LocalDate date;
    
    private int nbrCouvert;
    
    private float addition;
    
    private Client client;
    
    private TablePlace table;
    
 
}
