package com.restaurant.nour.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.restaurant.nour.entities.Met;

@NoRepositoryBean
public interface MetTypeMetRepository<T extends Met> extends CrudRepository<T, Integer> {

}
