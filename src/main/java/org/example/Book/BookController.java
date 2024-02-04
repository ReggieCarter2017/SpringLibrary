package org.example.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookModel> showAllBooks() {
        return bookService.getAllBooksFromService();
    }

    @GetMapping("/{id}")
    public BookModel showBookById(@PathVariable long id){
        return bookService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody String name) {
        bookService.addBookModel(name);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.deleteBookModelById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
