package com.restaurant.nour.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.restaurant.nour.dto.ClientDto;
import com.restaurant.nour.entities.Client;

@Component
public class ClientConverter {
	
	public ClientDto entityToDto(Client client) //methode pour convertir l'entité client à l'entité dto les champs à visualiser
	{
		ClientDto dto=new ClientDto();
		dto.setName(client.getName());
		dto.setPrenom(client.getPrenom());
		dto.setCourriel(client.getCourriel());
		dto.setTelephone(client.getTelephone());
		
		return dto;
	}
	
	public Client dtoToEntity(ClientDto clientdto) //methode pour convertir l'entité dto client à l'entité clientg les champs à visualiser
	{
		Client c=new Client();
		c.setName(clientdto.getName());
		c.setPrenom(clientdto.getPrenom());
		c.setCourriel(clientdto.getCourriel());
		c.setTelephone(clientdto.getTelephone());
		
		return c;
	}
	
    
	public List<ClientDto> entityToDto(List<Client> clients)
	
	{
		return clients.stream().map(x->entityToDto(x)).collect(Collectors.toList());
	}
	
	public List<Client> dtoToEntity(List<ClientDto> clientdto)
	{
		return clientdto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
	}
	
}
