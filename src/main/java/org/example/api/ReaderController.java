package org.example.api;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Issue;
import org.example.model.Reader;
import org.example.repository.IssueRepository;
import org.example.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
@Slf4j
public class ReaderController {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private IssueRepository issueRepository;
    @GetMapping("/{id}")
    public Reader showReaderById(@PathVariable long id){
        return readerRepository.getReaderById(id);
    }

    @PostMapping
    public String addReader(@RequestBody String name) {
        readerRepository.addReader(name);
        return "The reader has been added";
    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable long id) {
        readerRepository.deleteReader(id);
        return "The reader has been deleted";
    }

    @GetMapping("/{id}/issues")
    public List<Issue> showHisIssues(@PathVariable long id) {
        return issueRepository.getListOfIssuesByReadersId(id);
    }
}
