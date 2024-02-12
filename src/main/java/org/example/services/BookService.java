package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.models.BookModel;
import org.example.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private BookRepo itemRepository;
    public List<BookModel> getAllBooksFromService() {
        return itemRepository.findAll();
    }

    public BookModel findById(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id is provided");
        }
    }

    public void addBookModel(String name) {
        itemRepository.save(new BookModel(name));
    }

    public void deleteBookModelById(Long id) {
        itemRepository.deleteById(id);
    }
}
