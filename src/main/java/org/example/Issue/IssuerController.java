package org.example.Issue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issues")
public class IssuerController {
  @Autowired
  private IssuerService service;
  @Autowired
  private IssueRepository issueRepository;

  @PostMapping
  public ResponseEntity<IssueModel> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final IssueModel issueModel;

    try {
      issueModel = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body(issueModel);
  }

  @GetMapping("/{id}")
  public IssueModel getIssueById(@PathVariable long id) {
    return issueRepository.getIssueById(id);
  }

  @PutMapping("/{id}")
  public String returnIssueById(@PathVariable long id) {
    log.info("Получен запрос на возврат книги: issueId = {}", id);
    issueRepository.returnIssue(id);
    return "Книга была успешно возвращена";
  }

  @GetMapping
  public List<IssueModel> getAllIssues() {
    return issueRepository.getIssues();
  }

}
