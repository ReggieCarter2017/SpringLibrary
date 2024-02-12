package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.models.IssueModel;
import org.example.services.IssuerService;
import org.example.models.ReaderModel;
import org.example.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/readers")
@Slf4j
@Tag(name = "Reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssuerService issuerService;
    @GetMapping("/{id}")
    @Operation(summary = "get a reader by its id", description = "Получение читателя по его ID")
    public ReaderModel showReaderById(@PathVariable long id){
        return readerService.findById(id);
    }

    @PostMapping
    @Operation(summary = "create a reader", description = "Создание читателя для библиотеки")
    public ResponseEntity<?> addReader(@RequestBody String name) {
        readerService.addReaderModel(name);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "remove a reader by its id", description = "Удаление читателя из библиотеки по его ID")
    public ResponseEntity<?> deleteReader(@PathVariable long id) {
        readerService.deleteReaderModel(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "get all readers", description = "Получение всех пользователей, существующих в библиотеке")
    List<ReaderModel> getAllReaders() {
        return readerService.getAllReadersFromService();
    }

    @GetMapping("/{id}/issues")
    @Operation(summary = "show reader's issues", description = "Получение всех заказов пользователя по его ID")
    public List<IssueModel> showHisIssues(@PathVariable Long id) {
        List<IssueModel> issueModels = null;
        if ((issueModels = issuerService.getIssuesByReadersIdFromService(id)) != null){
            return issueModels;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
    }
}
