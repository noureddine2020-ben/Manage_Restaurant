package com.restaurant.nour.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.restaurant.nour.dto.TableDto;
import com.restaurant.nour.entities.TablePlace;

@Component
public class TableConverter {
	
	public TableDto entityToDto(TablePlace table) //methode pour convertir l'entité table à l'entité dto ->les champs à visualiser
	{
		TableDto dto=new TableDto();
		dto.setNbrCouvert(table.getNbrCouvert());
		dto.setType(table.getType());
		dto.setSupplement(table.getSupplement());
	
		
		return dto;
	}
	
	public TablePlace dtoToEntity(TableDto tabledto) //methode pour convertir l'entité dto table à l'entité -> les champs à visualiser
	{
		TablePlace tab=new TablePlace();
		tab.setNbrCouvert(tabledto.getNbrCouvert());
		tab.setType(tabledto.getType());
		tab.setSupplement(tabledto.getSupplement());
		
		
		return tab;
	}
	
    
	public List<TableDto> entityToDto(List<TablePlace> tables)
	
	{
		return tables.stream().map(x->entityToDto(x)).collect(Collectors.toList());
	}
	
	public List<TablePlace> dtoToEntity(List<TableDto> tabledto)
	{
		return tabledto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
	}
	

}
