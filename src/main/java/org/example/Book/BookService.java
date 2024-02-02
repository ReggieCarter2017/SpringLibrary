package org.example.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<BookModel> getAllBooksFromService() {
        return bookRepository.getAllBooks();
    }
}
