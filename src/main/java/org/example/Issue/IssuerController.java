package org.example.Issue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/issues")
@Slf4j
public class IssuerController {

  @Autowired
  private IssuerService service;


  @PostMapping
  public ResponseEntity<IssueModel> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
    final IssueModel issueModel;
    try {
      issueModel = service.issueBook(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body(issueModel);
  }

  @GetMapping("/{id}")
  public IssueModel getIssueById(@PathVariable Long id) {
    return service.getIssueByIdFromService(id);
  }

  @PutMapping("/{id}")
  public IssueModel returnIssueById(@PathVariable Long id) {
    log.info("Получен запрос на возврат книги: issueId = {}", id);
    return service.returnIssueByIdFromService(id);
  }

  @GetMapping
  public List<IssueModel> getAllIssues() {
    return service.getAllIssuesFromService();
  }

}
