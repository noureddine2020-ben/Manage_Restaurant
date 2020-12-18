package com.restaurant.nour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.nour.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	

}
