package com.U2W1D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.U2W1D5.model.User;
import com.U2W1D5.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired private UserRepository r;
	
	@Autowired @Qualifier("FakeUser") private ObjectProvider<User> fakeProvider;
	
	public User newFakeUser() {
		User u = fakeProvider.getObject();
		saveUser(u);
		log.info("Fake user succesfully created!");
		return u;
	}
	
	public void saveUser(User u) {
		r.save(u);
		log.info("User correctly saved!");
	}
	
	public User findById(Long id) {
		return r.findById(id).get();
	}
	
	public User findByUsername(String username) {
		return r.findByUsername(username);
	}
	
	public void deleteUser(Long id) {
		r.delete(findById(id));
		log.info("User succesfully deleted!");
	}
	
	public List<User> findAllUsers() {
		return (List<User>) r.findAll();
	}

}
