package com.restaurant.nour.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.nour.entities.Dessert;
import com.restaurant.nour.entities.Entre;
import com.restaurant.nour.entities.Met;
import com.restaurant.nour.entities.Plat;
import com.restaurant.nour.repository.DessertRepository;
import com.restaurant.nour.repository.EntreRepository;
import com.restaurant.nour.repository.MetRepository;
import com.restaurant.nour.repository.PlatRepository;



@RestController
@RequestMapping("/api/Met")
public class MetController {
	
	MetRepository repoMet;
	EntreRepository repoEntre;
	DessertRepository repoDessert;
	PlatRepository repoPlat;
	
	

 @Autowired
 public MetController(MetRepository repoMet, EntreRepository repoEntre, DessertRepository repoDessert,
			PlatRepository repoPlat) {
		super();
		this.repoMet = repoMet;
		this.repoEntre = repoEntre;
		this.repoDessert = repoDessert;
		this.repoPlat = repoPlat;
	}
 //************** chercher tous les plats***************//
 
 @GetMapping("/plat")
 public List<Plat> findAllPlats()
 {
	 return (List<Plat>) repoPlat.findAll();
 }
 
 //************** chercher tous les plats pour une période donnée***************//
 
 @GetMapping("/plat/{date1}/{date2}")
 public List<Plat> findAllPlatsByDate(@PathVariable(name="date") LocalDate date1,
		                              @PathVariable(name="date") LocalDate date2
		                            )
 {
	 return (List<Plat>) repoPlat.findByDate(date1,date2);
 }
 //************** recherhcer les plats par leurs nom*************//
 
 @GetMapping("/plat/{nom}")
 public List<Plat> findPlatParName(@PathVariable(name="nom") String nom)
 {
	 List<Plat> plats = findAllPlats();
	 List<Plat> platName=new ArrayList<>();
	 for (Plat plat : plats) {
		 if (plat.getNom().equalsIgnoreCase(nom)) 
			 platName.add(plat);
	 }
	 return platName;
 }
//***********************Création d'un plat******************//
 @PostMapping("/plat")
 public String createPlat(@RequestBody @Valid Plat platRequest)
 {
	 Plat plat = new Plat();
	 plat.setNom(platRequest.getNom());
	 plat.setPrix(platRequest.getPrix());
	 plat.setTickets(platRequest.getTickets());
	 repoPlat.save(plat);
	 
	 return "Plat crée avec succés!!";
 }
 
//*******************Création d'un dessert******************//
 @PostMapping("/dessert")
 public String createDessert(@RequestBody @Valid Dessert dessertRequest)
 {
	 Dessert dessert = new Dessert();
	 dessert.setNom(dessertRequest.getNom());
	 dessert.setPrix(dessertRequest.getPrix());
	 dessert.setTickets(dessertRequest.getTickets());
	 repoDessert.save(dessert);
	 
	 return "Dessert crée avec succés!!";
 }
 
//*******************Création d'un entre******************//
@PostMapping("/entre")
public String createEntre(@RequestBody @Valid Entre entreRequest)
{
	 Entre entre = new Entre();
	 entre.setNom(entreRequest.getNom());
	 entre.setPrix(entreRequest.getPrix());
	 entre.setTickets(entreRequest.getTickets());
	 repoEntre.save(entre);
	 
	 return "Entre crée avec succés!!";
}
//************recherche d'un Met*****///////////

@GetMapping("/{id}")
public String findMetById(@PathVariable(name="id") int id)
{   
	String metType = "";
	
	Optional<Met> opt= repoMet.findById(id);
	 Met met = null; 
	 met  = opt.orElseThrow(()-> new NoSuchElementException("Attention le met avec l'id "+id+ " n'existe pas"));
	 
	// get type of Met
     if (met instanceof Plat)
       metType = "Plat";
     else if (met instanceof Dessert)
       metType = "Dessert";
     else if (met instanceof Entre)
         metType = "Entre";
     
	return ("[ID : "+met.getId()+"-"+"Nom : " +met.getNom()+"-"+"Prix :"+met.getPrix()+"-"+"Type met : "+metType+" Ticket : "+met.getTickets()+"-"
			+"]");
}
//*************"**********suppression d'un met***********///

@DeleteMapping("/{id}")
public String deleteMet(@PathVariable(name="id") int id)
{
	Optional<Met> opt= repoMet.findById(id);
	 Met met = null; 
	 met  = opt.orElseThrow(()-> new NoSuchElementException("Attention le met avec l'id "+id+ " n'existe pas"));
	 repoMet.delete(met);
	 
	return "Met supprimé avec succés!!";
}

@PutMapping("/{id}")
public String updateMet(@PathVariable(name="id") int id,@RequestBody @Valid Met newmetRequest)
{
	 Optional<Met> opt= repoMet.findById(id);
	 Met met = null; 
	 met  = opt.orElseThrow(()-> new NoSuchElementException("Attention le met avec l'id "+id+ " n'existe pas"));
 
// switch on the met type
	 
if (met instanceof Plat) {
  Plat plat = (Plat)met;
  plat.setNom(newmetRequest.getNom());
  plat.setPrix(newmetRequest.getPrix());
}
if (met instanceof Entre) {
  Entre entre = (Entre)met;
  entre.setPrix(newmetRequest.getPrix());
  entre.setNom(newmetRequest.getNom());
}

if (met instanceof Dessert) {
	  Dessert dessert = (Dessert)met;
	  dessert.setPrix(newmetRequest.getPrix());
	  dessert.setNom(newmetRequest.getNom());
	}

// updates the user accordingly to its type (Plat or Dessert or Entre)
     repoMet.save(met);

   return "Mise à jour faite avec succés";

}
//********************plat le plus acheté : l'idé est de cherher le plat qui se repètent le plus**************************//

@GetMapping("/plat/most")
public String mostPlatBough()
{
	List<Plat> list=(List<Plat>) findAllPlats();
	List<String> platNames = new ArrayList<String>();
	for(Plat plat : list)
	{
		platNames.add(plat.getNom());
	}
	Map<String, Long> data =count((platNames).stream()); // compter le nombre des plats pour chaque plat &stocker le resultat dans un map
	
	Entry<String, Long> entry = data.entrySet().stream().max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue())).get(); // cherhcer la valeur maximale
	 
	return("Le Plat le plus acheté est (Nom du plat=" + entry.getKey() + ", le nombes vendus=" + entry.getValue() + ")");

	//System.out.println(((count((platNames).stream()))));
	
}

//*********** méthode suivante qui compte le nombre d’occurrences de chaque valeur*******///

public static <E> Map<E, Long> count(Stream<E> stream) {
    return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
}

@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
