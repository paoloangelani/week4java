package com.U2W1D5.runner;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.U2W1D5.model.Building;
import com.U2W1D5.model.Reservation;
import com.U2W1D5.model.Station;
import com.U2W1D5.model.StationType;
import com.U2W1D5.model.User;
import com.U2W1D5.service.BuildingService;
import com.U2W1D5.service.ReservationService;
import com.U2W1D5.service.StationService;
import com.U2W1D5.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Runner implements ApplicationRunner {

	public static Scanner s = new Scanner(System.in);
	User user = null;

	@Autowired
	UserService userDAO;

	@Autowired
	BuildingService buildingDAO;

	@Autowired
	StationService stationDAO;

	@Autowired
	ReservationService reservationDAO;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.info("### Main runner executed. ###");

		Building b = new Building("Doge Tower", "Doge Avenue, 77", "Rome");
		Station st = new Station("Large Openspace room", StationType.Openspace, 50, b);
//		buildingDAO.saveBuilding(b);
//		stationDAO.saveStation(st);
		
//		buildingDAO.newFakeBuilding();
//		stationDAO.newFakeStation()
//		userDAO.newFakeUser();
		
		System.out.println("Welcome! On this you can interface with Stations and manage your reservations.");
		do {
			System.out.println("What would you like to do?");
			System.out.println("\t 1 - Login by id");
			System.out.println("\t 2 - Register");
			int input = s.nextInt();
			switch (input) {
			case 1 -> {
				System.out.println("Please insert your id");
				Long id = s.nextLong();
				try {
					user = userDAO.findById(id);
					break;
				} catch (Exception e) {
					log.error("No user found with given id");
				}
			}
			case 2 -> {
				s.nextLine();
				System.out.println("Please insert your full name");
				String name = s.nextLine();
				System.out.println("Please insert your email");
				String email = s.nextLine();
				System.out.println("Please insert a username");
				String username = s.nextLine();
				try {
					userDAO.saveUser(new User(name, username, email));
					user = userDAO.findByUsername(username);
					System.out.println("Thank you for registering! Your login ID is: " + user.getId());
				} catch (Exception e) {
					log.error("There was an error on registration!");
				}

			}
			default -> {
				log.error("Not an acceptable option!");
				break;
			}
			}
		} while (user == null);
		while (true) {
			functionsMenu();
		}
	}

	public void functionsMenu() {
		System.out.println("\n \t Welcome " + user.getUsername() + "! What would you like to do?");
		System.out.println("\t 1 - View all stations");
		System.out.println("\t 2 - Search station by city");
		System.out.println("\t 3 - Search station by type");
		System.out.println("\t 4 - Create a new reservation");
		System.out.println("\t 5 - Delete a reservation");
		System.out.println("\t 6 - Check your reservations");
		System.out.println("\t 0 - Exit the application");
		int choice = s.nextInt();
		s.nextLine();
		switch (choice) {
		case 1 -> {
			List<Station> allStations = stationDAO.findAllStations();
			allStations.forEach(elem -> System.out.println(elem));
		}
		case 2 -> {
			System.out.println("\n \t Insert city name:");
			String city = s.nextLine();
			List<Station> foundStations = stationDAO.findByCity(city);
			if (foundStations.size() > 0) {
				foundStations.forEach(elem -> System.out.println(elem));
			} else {
				log.error("No cities found for given location.");
			}
			break;
		}
		case 3 -> {
			System.out.println("\n \t Please select Station type:");
			System.out.println("1 - Private || 2 - Meeting Room || 3 - Openspace");
			int type = s.nextInt();
			s.nextLine();
			StationType selectedType = null;
			switch (type) {
			case 1 -> {
				selectedType = StationType.Private;
			}
			case 2 -> {
				selectedType = StationType.MeetingRoom;
			}
			case 3 -> {
				selectedType = StationType.Openspace;
			}
			default -> {
				log.error("Not an acceptable value!");
			}
			}
			List<Station> foundStations = stationDAO.findByType(selectedType);
			if (foundStations.size() > 0) {
				foundStations.forEach(elem -> System.out.println(elem));
			} else {
				log.error("No cities found for given location.");
			}
		}
		case 4 -> {
			System.out.println("\n \t Please insert the ID of the Station you'd like to reserve.");
			Long stationID = s.nextLong();
			Station station = null;
			try {
				station = stationDAO.findStation(stationID);
			} catch (Exception e) {
				log.error("No Station found with given ID!");
				break;
			}
			System.out.println("\n \t Insert the date for your reservation");
			LocalDate date = createDate();
			reservationDAO.saveReservation(new Reservation(user, station, date));
		}
		case 5 -> {
			System.out.println("Please insert the ID of the reservation you'd like to cancel");
			Long id = s.nextLong();
			reservationDAO.deleteReservation(id);
		}
		case 6 -> {
			List<Reservation> userReservations = reservationDAO.findByUser(user);
			if (userReservations.size() > 0) {
				userReservations.forEach(elem -> System.out.println(elem));
			} else {
				System.out.println("It seems you have no reservations yet!");
			}
		}
		case 0 -> {
			System.out.println("Thank you for your time!");
			log.warn("Quitting application...");
			System.exit(0);
		}
		default -> {
			log.error("Not an acceptable option!");
			break;
		}
		}
	}
	
	public static LocalDate createDate() {
		System.out.println("\t Insert Day(DD):");
		int day = s.nextInt();
		System.out.println("\t Insert Month (MM):");
		int month = s.nextInt();
		System.out.println("\t Insert Year (YYYY):");
		int year = s.nextInt();
		return LocalDate.of(year, month, day);
	}
}
