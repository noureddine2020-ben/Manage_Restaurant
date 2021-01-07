package com.restaurant.nour.repository;




import javax.transaction.Transactional;

import com.restaurant.nour.entities.Met;


@Transactional
public interface MetRepository extends MetTypeMetRepository<Met>{
		  

}
