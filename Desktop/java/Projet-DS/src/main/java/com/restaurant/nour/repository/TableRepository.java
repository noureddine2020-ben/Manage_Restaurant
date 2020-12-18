package com.restaurant.nour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.nour.entities.TablePlace;

public interface TableRepository extends JpaRepository<TablePlace, Integer>{

}
