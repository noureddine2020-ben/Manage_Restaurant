package com.restaurant.nour.services;

import java.util.List;


import com.restaurant.nour.entities.Met;


public interface MetService {
	  
	public List<Met> rechercheToutLesMet();
	public Met rechercheParIdMet(int id);
	public Met ajouterMet(Met met);
	public Met miseAjourMet(int id,Met met);
	public Met supprimerMet(int id);


}
