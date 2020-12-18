package com.restaurant.nour.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablePlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numTable;
	
	
	@Column(length = 50)
	private int nbrCouvert;
	
	@NotBlank
	private String type;
	
	@Column(length = 30)
	private float supplement;
	
	@OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
	private List<Ticket> tickets;

}
