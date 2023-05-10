package com.U2W1D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.U2W1D5.model.Building;
import com.U2W1D5.model.Station;
import com.U2W1D5.model.StationType;
import com.U2W1D5.repository.BuildingRepository;
import com.U2W1D5.repository.StationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StationService {
	
	@Autowired private StationRepository stationRepo;
	@Autowired private BuildingRepository buildingRepo;
	
	@Autowired @Qualifier("FakeStation") private ObjectProvider<Station> fakeProvider;
	
	public Station newFakeStation() {
		Station s = fakeProvider.getObject();
		Building b = buildingRepo.getRandomBuilding();
		s.setBuilding(b);
		saveStation(s);
		log.info("Fake station succesfully created!");
		return s;
	}
	
	public void saveStation(Station s) {
		stationRepo.save(s);
		log.info("Station saved succesfully!");
	}
	
	public void deleteStation(Station s) {
		stationRepo.delete(s);
		log.info("Station deleted succesfully!");
	}
	
	public Station findStation(Long id) {
		return stationRepo.findById(id).get();
	}
	
	public List<Station> findAllStations() {
		return (List<Station>) stationRepo.findAll();
	}
	
	public List<Station> findByCity(String city) {
		return (List<Station>) stationRepo.findByCity(city);
	}
	
	public List<Station> findByType(StationType type) {
		return (List<Station>) stationRepo.findByType(type);
	}
}
