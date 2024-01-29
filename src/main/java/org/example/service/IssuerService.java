package org.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.api.IssueRequest;
import org.example.model.Issue;
import org.example.repository.BookRepository;
import org.example.repository.IssueRepository;
import org.example.repository.ReaderRepository;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  public int checkReaderForBorrowedBooks(long id) {
    return issueRepository.readerHasBorrowedBooksCheck(id);
  }

}
