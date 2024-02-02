package org.example.api;

import lombok.extern.slf4j.Slf4j;
import org.example.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import org.example.model.*;
import org.example.service.IssuerService;

@Slf4j
@RestController
@RequestMapping("/issues")
public class IssuerController {
  @Autowired
  private IssuerService service;
  @Autowired
  private IssueRepository issueRepository;

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;

    if (service.checkReaderForBorrowedBooks(request.getReaderId()) == 2) {
      System.out.println("Проверка прошла");
      return ResponseEntity.notFound().build();
    }

    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }

  @GetMapping("/{id}")
  public Issue getIssueById(@PathVariable long id) {
    return issueRepository.getIssueById(id);
  }

  @PutMapping("/{id}")
  public String returnIssueById(@PathVariable long id) {
    log.info("Получен запрос на возврат книги: issueId = {}", id);
    issueRepository.returnIssue(id);
    return "Книга была успешно возвращена";
  }

  @GetMapping
  public List<Issue> getAllIssues() {
    return issueRepository.getIssues();
  }

}
