package com.brad.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brad.bookclub.models.Book;
import com.brad.bookclub.models.User;
import com.brad.bookclub.services.BookService;
import com.brad.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookServ;
	
	@Autowired
	private UserService userServ;
	
//	Display the main page for those logged in with all books
	@GetMapping("/books")
	public String success(HttpSession session, Model model, RedirectAttributes flash) {
		
		if(session.getAttribute("loggedIn") == null) {
			flash.addFlashAttribute("error", "Please log in.");
			return "redirect:/";			
		}
		
		User loggedIn = userServ.findUser((Long)session.getAttribute("loggedIn"));
		model.addAttribute("loggedInUser", loggedIn);
		model.addAttribute("books", bookServ.findAllBooks());
		
		return "dashboard.jsp";
	}
	
//	Display new book form
	@GetMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book) {
		
		return "book.jsp";
	}
	
//	Create a book
	@PostMapping("/books/new")
	public String createNewBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("book", book);
			return "book.jsp";
		}
		
		bookServ.createBook(book);
		return "redirect:/books";
	}
	
//	Show one book's information
	@GetMapping("/books/{id}")
	public String findOneBook(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("book", bookServ.findBook(id));
		return "show.jsp";
	}
	
//	Display update book page
	@GetMapping("/books/{id}/edit")
	public String displayEdit(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Book book = bookServ.findBook(id);
		
		Long userId = book.getUser().getId();
		
		if(!userId.equals(session.getAttribute("loggedIn"))) {
			return "redirect:/logout";
		}
		
		model.addAttribute("book", book);
		return "edit.jsp";
	}
	
//	Update a book
	@PutMapping("/books/edit")
	public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute(book);
			return "edit.jsp";
		}
		
		bookServ.updateBook(book);
		return "redirect:/books";
	}
	
//	Delete a book
	@DeleteMapping("/books/{id}/delete")
	public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
		
		Book book = bookServ.findBook(id);
		
		Long userId = book.getUser().getId();
		
		if(!userId.equals(session.getAttribute("loggedIn"))) {
			return "redirect:/logout";
		}
		
		bookServ.deleteBookById(id);
		return "redirect:/books";
	}
}
