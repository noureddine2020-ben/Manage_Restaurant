package com.restaurant.nour.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.nour.converter.ClientConverter;
import com.restaurant.nour.dto.ClientDto;
import com.restaurant.nour.entities.Client;
import com.restaurant.nour.services.ClientImpService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	ClientImpService service;
	
	@Autowired
	ClientConverter converter;
    
	@Autowired
	public ClientController(ClientImpService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<ClientDto> getAll()
	{ 
		List<Client> clients=service.rechercheTousLesClient();
		return converter.entityToDto(clients);
		
	}
    
	@GetMapping("/{id}")
	public ClientDto getClientById(@PathVariable("id") int id)
	{
		Client client= service.rechercheParId(id);
		return converter.entityToDto(client);
	}
	
	
	@PostMapping
	public ClientDto addClient(@RequestBody @Validated ClientDto clientRequest)
	{   
		Client client= converter.dtoToEntity(clientRequest);
		client=service.ajouterClient(client);
		return converter.entityToDto(client);
	}
	
	@PutMapping("/{id}")
	public ClientDto updateClient(@PathVariable(name = "id") int id, @RequestBody @Validated ClientDto clientRequest)
	{
		Client client =converter.dtoToEntity(clientRequest);
		client=service.miseAjourClient(id, client);
		return converter.entityToDto(client);
	}
	
	@DeleteMapping("/{id}")
	public ClientDto deleteClient(@PathVariable(name = "id") int id,@RequestBody ClientDto clientRequest)
	{
		Client oldclient= converter.dtoToEntity(clientRequest);
		oldclient=service.supprimerClient(id);
		return converter.entityToDto(oldclient);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
