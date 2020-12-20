package com.restaurant.nour.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import lombok.Data;


@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_of_Met")
@Table(name = "TableMet")
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
	@JsonIgnore
    private List<Ticket> tickets;
	

}
