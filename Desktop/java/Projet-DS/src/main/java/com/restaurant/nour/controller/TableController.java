package com.restaurant.nour.controller;

import java.util.List;
import java.util.NoSuchElementException;


import javax.validation.Valid;

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

import com.restaurant.nour.converter.TableConverter;
import com.restaurant.nour.dto.TableDto;
import com.restaurant.nour.entities.TablePlace;
import com.restaurant.nour.services.TableImpService;

@RestController
@RequestMapping("/api/table")
public class TableController {
	
    TableImpService service;
    TableConverter tabConverter;

	
    @Autowired
    public TableController(TableImpService service, TableConverter tabConverter) {
		super();
		this.service = service;
		this.tabConverter = tabConverter;
	}



	@GetMapping
	public List<TableDto> findAllTables()
	{
		List<TablePlace> tables=service.rechercheToutesTables();
		return tabConverter.entityToDto(tables);
	}
	
	@GetMapping("/{id}")
	public TableDto findByNumTbale(@PathVariable(name = "id") int id)
	{ 
	   TablePlace table=service.rechercheParNumT(id);
	   return tabConverter.entityToDto(table);
	}
	
	@PostMapping
	public TableDto addTable(@RequestBody @Valid TableDto requesTable)
	{
		TablePlace table= tabConverter.dtoToEntity(requesTable);
		 service.ajouterTable(table);
		 return tabConverter.entityToDto(table);
	}
	
	@PutMapping("/{id}")
	public TableDto updateTable(@PathVariable(name = "id") int id, @RequestBody TableDto tablDto)
	{
		TablePlace table = tabConverter.dtoToEntity(tablDto);
		 table = service.miseAjourTable(id, table);
		 return tabConverter.entityToDto(table);
	}
	
	@DeleteMapping("/{id}")
	public TableDto deleteTable(@PathVariable(name = "id") int id,@RequestBody TableDto tableDto)
	{
		TablePlace tab = tabConverter.dtoToEntity(tableDto);
		tab = service.supprimerTable(id);
		return tabConverter.entityToDto(tab);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
