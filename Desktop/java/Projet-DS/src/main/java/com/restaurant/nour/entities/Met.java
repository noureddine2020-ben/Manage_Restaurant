package com.restaurant.nour.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.persistence.JoinColumn;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Met {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 50)
	@NotBlank
	@Pattern(regexp="^[A-Za-z]*$",message = "données invalides") // name with caracter only
	private String nom;
	
	@Column(length = 30)
	private float prix;
	
	@ManyToMany
	@JoinTable(name = "Tickets_Mets",joinColumns =@JoinColumn(name="id"),inverseJoinColumns =@JoinColumn(name="numero"))
    private List<Ticket> tickets;
	
	

}
