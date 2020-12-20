package com.restaurant.nour.repository;




import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.restaurant.nour.entities.Met;
import com.restaurant.nour.entities.Plat;


@NoRepositoryBean
public interface MetTypeMetRepository<T extends Met> extends CrudRepository<T, Integer> {
	
	
	/**@Query("SELECT TOP 1 "
			+ "p.Name" 
			+ "FROM plat p" 
			+ "INNER JOIN ticket t" 
			+"INNER JOIN table_met m"
			+"ON p.id = m.id"
			+"ON m.id = t.id"
			+"WHERE t.date between(?1,?2)"
			+"GROUP BY p.nom\r\n"
			+"ORDER BY COUNT(*) DESC")
	public Plat mostBought (LocalDate dateDebut, LocalDate dateFin);*/
	
	
	
}
