package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.models.IssueModel;
import org.example.models.IssueRequest;
import org.example.services.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/issues")
@Slf4j
@Tag(name = "Issue")
public class IssuerController {

  @Autowired
  private IssuerService service;


  @PostMapping
  @Operation(summary = "make an order", description = "Создание заказа на получение книги клиентом")
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
  @Operation(summary = "get an issue by its id", description = "Получение заказа по его ID")
  public IssueModel getIssueById(@PathVariable Long id) {
    return service.getIssueByIdFromService(id);
  }

  @PutMapping("/{id}")
  @Operation(summary = "close the issue", description = "Закрытие заказа по его ID")
  public IssueModel returnIssueById(@PathVariable Long id) {
    log.info("Получен запрос на возврат книги: issueId = {}", id);
    return service.returnIssueByIdFromService(id);
  }

  @GetMapping
  @Operation(summary = "get all issues", description = "Получение всех заказов из библиотеки")
  public List<IssueModel> getAllIssues() {
    return service.getAllIssuesFromService();
  }

}
