package com.U2W1D5.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.U2W1D5.model.Building;
import com.U2W1D5.model.Station;
import com.U2W1D5.model.StationType;
import com.github.javafaker.Faker;

@Configuration
public class StationConfig {
	
	@Bean("Station")
	@Scope("prototype")
	public Station paramsStation(String description, StationType type, Integer max_seats, Building building) {
		return new Station(description, type, max_seats, building);
	}
	
	@Bean("FakeStation")
	@Scope("prototype")
	public Station fakeStation() {
		Faker f = Faker.instance(new Locale("it-IT"));
		Station s = new Station();
		s.setDescription(f.lorem().word());
		int seats = f.number().numberBetween(1, 50);
		s.setMax_seats(seats);
		if (seats <= 10 ) {
			s.setType(StationType.Private);
		} else if (seats <= 30) {
			s.setType(StationType.MeetingRoom);
		} else {
			s.setType(StationType.Openspace);
		}
		return s;
	}

}
