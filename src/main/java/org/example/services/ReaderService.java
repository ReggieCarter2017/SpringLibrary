package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.models.ReaderModel;
import org.example.repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {
    @Autowired
    private ReaderRepo readerRepo;
    public List<ReaderModel> getAllReadersFromService() {
        return readerRepo.findAll();
    }
    public ReaderModel getReaderByIdFromService(Long id) {
        Optional<ReaderModel> x;
        if ((x = readerRepo.findById(id)) != null) {
            return x.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The reader not found");
        }
    }

    public void addReaderModel(String name) {
        readerRepo.save(new ReaderModel(name));
    }

    public void deleteReaderModel(Long id) {
        readerRepo.deleteById(id);
    }

    public ReaderModel findById(Long id) {
        Optional<ReaderModel> x = null;
        if ((x = readerRepo.findById(id)) != null) {
            return x.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

}
