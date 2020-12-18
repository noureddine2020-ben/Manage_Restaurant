package com.restaurant.nour.entities;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private int id;
	
	@Column(length = 50,nullable = false)
	@NotBlank(message = "Nom obligatoire")
	@Pattern(regexp="^[A-Za-z]*$",message = "données invalides") // name with caracter only
	private String name;
	
	@Column(length = 50,nullable = false)
	@NotBlank(message = "prenom obligatoire")
	@Pattern(regexp="^[A-Za-z]*$",message = "données invalides") // name with caracter only
	private String prenom;
	
	@Column(nullable = true)
	@Past
	private LocalDate dateDeNaissance;
	
	@Column(length = 50,nullable = true)
	@Email
	private String courriel;
	
	@Column(length = 10,nullable = true)
	private String telephone;
	
	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Ticket> tickets;

}
