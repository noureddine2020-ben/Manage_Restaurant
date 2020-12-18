package com.restaurant.nour.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.nour.entities.Dessert;
import com.restaurant.nour.entities.Entre;
import com.restaurant.nour.entities.Met;
import com.restaurant.nour.entities.Plat;
import com.restaurant.nour.repository.MetRepository;

@Service
public class MetImpService implements MetService {
     
	MetRepository reposMet;
	
	@Autowired
	public MetImpService(MetRepository reposMet) {
		super();
		this.reposMet = reposMet;
	}

	@Override
	public List<Met> rechercheToutLesMet() {
		
		return reposMet.findAll();
	}

	@Override
	public Met rechercheParIdMet(int id) {
		Optional<Met> opt=reposMet.findById(id);
		Met met=null;
		met=opt.orElseThrow(()-> new NoSuchElementException("impossible de trouver le met "+id+" !!"));
		return met;
	}

	@Override
	public Met ajouterMet(Met met) {
		
		return reposMet.save(met);
		
	}

	@Override
	public Met miseAjourMet(int id, Met met) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Met supprimerMet(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
