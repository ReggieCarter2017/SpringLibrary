package org.example.Reader;

import lombok.extern.slf4j.Slf4j;
import org.example.Issue.IssueModel;
import org.example.Issue.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/readers")
@Slf4j
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssuerService issuerService;
    @GetMapping("/{id}")
    public ReaderModel showReaderById(@PathVariable long id){
        return readerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addReader(@RequestBody String name) {
        readerService.addReaderModel(name);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReader(@PathVariable long id) {
        readerService.deleteReaderModel(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping List<ReaderModel> getAllReaders() {
        return readerService.getAllReadersFromService();
    }

    @GetMapping("/{id}/issues")
    public List<IssueModel> showHisIssues(@PathVariable Long id) {
        List<IssueModel> issueModels = null;
        if ((issueModels = issuerService.getIssuesByReadersIdFromService(id)) != null){
            return issueModels;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
    }
}
