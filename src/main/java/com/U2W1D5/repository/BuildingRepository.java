package com.U2W1D5.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.U2W1D5.model.Building;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long>{
	
	@Query(value = "SELECT b FROM Building b ORDER BY RANDOM() LIMIT 1")
	public Building getRandomBuilding();

}
