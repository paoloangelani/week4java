package com.U2W1D5.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.U2W1D5.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query(value = "SELECT u FROM User u WHERE username = :username")
	public User findByUsername(String username);
}
