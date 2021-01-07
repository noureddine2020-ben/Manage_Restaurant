package com.restaurant.nour.repository;


import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import com.restaurant.nour.entities.Plat;

@Transactional
public interface PlatRepository extends MetTypeMetRepository<Plat> {
	
    
	@Query(value = "select nom from table_met m,ticket t,tickets_mets tm\r\n" + 
			"where type_of_met = 'Plat'\r\n" + 
			"and date between(?1,?2) \r\n" + 
			"and m.id=tm.id\r\n" + 
			"and t.nume_ticket = tm.id", nativeQuery = true)
	  List<Plat>findByDate(LocalDate date1,LocalDate date2);
	  
	}

