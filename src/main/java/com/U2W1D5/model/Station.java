package com.U2W1D5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Station {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private StationType type;
	
	private Integer max_seats;
	
	@ManyToOne
	@JoinColumn
	private Building building;

	public Station(String description, StationType type, Integer max_seats, Building building) {
		super();
		this.description = description;
		this.type = type;
		this.max_seats = max_seats;
		this.building = building;
	}
}
