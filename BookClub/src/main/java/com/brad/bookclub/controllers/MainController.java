package com.brad.bookclub.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brad.bookclub.models.LoginUser;
import com.brad.bookclub.models.User;
import com.brad.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
//	Main index route with Login and Registration
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "index.jsp";
	}
	
//	Validations and user account creation
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, Model model, HttpSession session) {
		
		Boolean isValid = userServ.isValid(result, newUser);
		
		if(result.hasErrors() || isValid != true) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		userServ.createUser(newUser);
		
		session.setAttribute("loggedIn", newUser.getId());
		
		return "redirect:/books";
	}
	
//	Login validations
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			BindingResult result, Model model, HttpSession session) {
		
		Boolean validUser = userServ.validLogin(result, newLogin, session);
		
		if(result.hasErrors() || !validUser) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		return "redirect:/books";
	}
	
//	Logout 
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/";
	}
}
