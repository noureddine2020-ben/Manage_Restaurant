package com.restaurant.nour.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.nour.entities.Met;
import com.restaurant.nour.services.MetService;

@RestController
@RequestMapping("/api/Met")
public class MetController {
	
	
	MetService service;
    
	@Autowired
	public MetController(MetService service) {
		super();
		this.service = service;
	}

 @GetMapping
 public List<Met> GetAllMets()
 {
	   return service.rechercheToutLesMet();
 }
 
 @GetMapping("/{id}")
 public Met GetMetById(@PathVariable(name="id") int id)
 {
	return service.rechercheParIdMet(id) ;
 }
 
 @PostMapping
 public Met AddMet(@RequestBody @Valid Met metRequest)
 {
	return service.ajouterMet(metRequest); 
 }
 
 
 @ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
