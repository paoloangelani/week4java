package com.U2W1D5.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.U2W1D5.model.Building;
import com.github.javafaker.Faker;

@Configuration
public class BuildingConfig {
	
	@Bean("Building")
	@Scope("prototype")
	public Building paramsBuilding(String name, String address, String city) {
		return new Building(name, address, city);
	}
	
	@Bean("FakeBuilding")
	@Scope("prototype")
	public Building fakeBuilding() {
		Faker f = Faker.instance(new Locale("it-IT"));
		Building b = new Building();
		b.setName(f.company().name());
		b.setAddress(f.address().streetAddress());
		b.setCity(f.address().city());
		return b;
	}
}
