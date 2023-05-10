package com.U2W1D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.U2W1D5.model.Building;
import com.U2W1D5.repository.BuildingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuildingService {
	
	@Autowired private BuildingRepository r;
	
	@Autowired @Qualifier("FakeBuilding") private ObjectProvider<Building> fakeProvider;
	
	public void newFakeBuilding() {
		Building b = fakeProvider.getObject();
		saveBuilding(b);
		log.info("Fake building succesfully created!");
	}
	
	public void saveBuilding(Building b) {
		r.save(b);
		log.info("Building correctly saved!");
	}
	
	public Building findBuilding(Long id) {
		return r.findById(id).get();
	}
	
	public void deleteUser(Long id) {
		r.delete(findBuilding(id));
		log.info("Building succesfully deleted!");
	}
	
	public void updateBuilding(Building b) {
		r.save(b);
		log.info("Building updated!");
	}
	
	public List<Building> findAllBuildings() {
		return (List<Building>) r.findAll();
	}
}
