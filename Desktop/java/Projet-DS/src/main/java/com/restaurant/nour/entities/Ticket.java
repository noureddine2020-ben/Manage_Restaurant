package com.restaurant.nour.entities;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.nour.dto.ClientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "numeTicket")
	private int numero;
	
	@PastOrPresent
    private LocalDate date;
    
   
    @Column(length =30,nullable = false)
    private int nbrCouvert;
    
   
    @Column(length = 20,nullable = false)
    private float addition;
    
    @ManyToOne
    @JsonIgnore
    private Client client;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "numTable")
    private TablePlace table;
    
    @ManyToMany(mappedBy = "tickets",cascade = CascadeType.REMOVE)
    private List<Met> Mets;
	
	
}
