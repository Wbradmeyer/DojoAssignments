package com.brad.bookclub.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.brad.bookclub.models.LoginUser;
import com.brad.bookclub.models.User;
import com.brad.bookclub.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	
	public User findUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	
	public Boolean isValid(BindingResult result, User newUser) {
		
		User emailInUse = userRepo.findByEmail(newUser.getEmail()).orElse(null);
		
		if(emailInUse != null) {
			result.rejectValue("email", "Exists", "This email is already in use.");
		}
		else if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Confirm Password must match Password!");
		}
		
		return true;
	}

	
	public Boolean validLogin(BindingResult result, LoginUser user, HttpSession session) {
		
		if(result.hasErrors()) {
			return false;
		}
		
		User potentialUser = userRepo.findByEmail(user.getEmail()).orElse(null);
		
		if(potentialUser == null) {
			result.rejectValue("email", "Exists", "There is no account with this email.");
			return false;
		}
		
		if(!BCrypt.checkpw(user.getPassword(), potentialUser.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password!");
			return false;
		}
		
		session.setAttribute("loggedIn", potentialUser.getId());
		
		return true;
	}
}
