package com.U2W1D5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.U2W1D5.model.Station;
import com.U2W1D5.model.StationType;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {

	@Query(value = "SELECT s FROM Station s INNER JOIN s.building b WHERE LOWER(b.city) LIKE LOWER(concat('%', :city,'%'))")
	public List<Station> findByCity(String city);
	
	@Query(value = "SELECT s FROM Station s WHERE s.type LIKE :type")
	public List<Station> findByType(StationType type);
}
