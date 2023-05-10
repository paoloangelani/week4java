package com.U2W1D5.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.U2W1D5.model.Reservation;
import com.U2W1D5.model.Station;
import com.U2W1D5.model.User;

@Configuration
public class ReservationConfig {

	@Bean("Reservation")
	@Scope("prototype")
	public Reservation reservation(User user, Station station, LocalDate date) {
		return new Reservation(user, station, date);
	}
}
