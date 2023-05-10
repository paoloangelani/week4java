package com.U2W1D5.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.U2W1D5.model.Reservation;
import com.U2W1D5.model.Station;
import com.U2W1D5.model.User;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>{
	
	@Query(value="SELECT r FROM Reservation r INNER JOIN r.user u WHERE u = :user")
	public List<Reservation> findByUser(User user);

	@Query(value="SELECT r FROM Reservation r INNER JOIN r.user u WHERE u = :user AND r.date = :date")
	public List<Reservation> findByUserAndDate(User user, LocalDate date);

	@Query(value="SELECT r FROM Reservation r INNER JOIN r.station st WHERE st = :stat AND r.date = :date")
	public List<Reservation> findByStatAndDate(Station stat, LocalDate date);
}
