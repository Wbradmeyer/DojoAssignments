package com.brad.bookclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.brad.bookclub.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();
	
	Optional<User> findByEmail(String email);
}