package org.example.api;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/{id}")
    public Book showBookById(@PathVariable long id){
        return bookRepository.getBookById(id);
    }

    @PostMapping
    public String addBook(@RequestBody String name) {
        bookRepository.addBook(name);
        return "The book has been added";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        bookRepository.deleteBook(id);
        return "The book has been deleted";
    }
}
