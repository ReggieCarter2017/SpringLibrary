package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.models.BookModel;
import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "get all books", description = "Получает все существующие книги")
    public List<BookModel> showAllBooks() {
        return bookService.getAllBooksFromService();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a book by its id", description = "Получает книгу по его ID")
    public BookModel showBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @Operation(summary = "create a book", description = "Создает книгу")
    public ResponseEntity<?> addBook(@RequestBody String name) {
        bookService.addBookModel(name);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a book by its id", description = "Удаление книги по его ID")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.deleteBookModelById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
