package com.brad.bookclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brad.bookclub.models.Book;
import com.brad.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
//	Create Book Services
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	
//	Read Book Services
	public Book findBook(Long id) {
		return bookRepo.findById(id).orElse(null);
	}
	
	public List<Book> findAllBooks() {
		return bookRepo.findAll();
	}
	
//	Update Book Services
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}
	
//	Delete Book Services
	public void deleteBookById(Long id) {
		bookRepo.deleteById(id);
	}
}
