package com.U2W1D5.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.U2W1D5.model.Reservation;
import com.U2W1D5.model.Station;
import com.U2W1D5.model.User;
import com.U2W1D5.repository.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationService {
	
	@Autowired private ReservationRepository repo;
	
	public void saveReservation(Reservation r) {
		List<Reservation> userList = findByUserAndDate(r.getUser(), r.getDate());
		List<Reservation> stationList = findByStatAndDate(r.getStation(), r.getDate());
		if (userList.size() > 0) {
			log.error("There already seems to be a reservation for the given date");
		} else if (stationList.size() > 0) {
			log.error("We're sorry, the Station is already occupied for the given date");
		} else {
			repo.save(r);
			log.info("Successfully created a new reservation for date: " + r.getDate());
		}
	}
	
	public List<Reservation> findByUser(User u) {
		return (List<Reservation>) repo.findByUser(u);
	}
		
	public void deleteReservation(Long id) {
		repo.deleteById(id);
	}
	
	public List<Reservation> findByUserAndDate(User u, LocalDate d) {
		return (List<Reservation>) repo.findByUserAndDate(u, d);
	}
	
	public List<Reservation> findByStatAndDate(Station s, LocalDate d) {
		return (List<Reservation>) repo.findByStatAndDate(s, d);
	}
	
	public List<Reservation> findAll() {
		return (List<Reservation>) repo.findAll();
	}

}
