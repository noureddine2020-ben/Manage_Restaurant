package com.restaurant.nour.repository;



import org.springframework.transaction.annotation.Transactional;

import com.restaurant.nour.entities.Dessert;

@Transactional
public interface DessertRepository extends MetTypeMetRepository<Dessert> {

}
