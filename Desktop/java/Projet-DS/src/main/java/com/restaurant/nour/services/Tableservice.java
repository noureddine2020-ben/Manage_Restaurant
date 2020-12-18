package com.restaurant.nour.services;

import java.util.List;

import com.restaurant.nour.entities.TablePlace;



public interface Tableservice {
	
	public List<TablePlace> rechercheToutesTables();
	public TablePlace rechercheParNumT(int num);
	public TablePlace ajouterTable(TablePlace table);
	public TablePlace miseAjourTable(int num,TablePlace table);
	public TablePlace supprimerTable(int num);


}
