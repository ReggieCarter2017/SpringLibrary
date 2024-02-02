package org.example.Reader;

import lombok.RequiredArgsConstructor;
import org.example.Book.BookModel;
import org.example.Book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;
    public List<ReaderModel> getAllReadersFromService() {
        return readerRepository.getAllReaderModels();
    }
    public ReaderModel getReaderByIdFromService(long id) {
        return readerRepository.getReaderById(id);
    }




}
