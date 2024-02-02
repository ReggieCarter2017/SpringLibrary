package org.example.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/{id}")
    public BookModel showBookById(@PathVariable long id){
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
