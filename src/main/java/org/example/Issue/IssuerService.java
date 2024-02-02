package org.example.Issue;

import lombok.RequiredArgsConstructor;
import org.example.Book.BookModel;
import org.springframework.stereotype.Service;
import org.example.Book.BookRepository;
import org.example.Reader.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public IssueModel issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    IssueModel issueModel = new IssueModel(request.getBookId(), request.getReaderId());
    issueRepository.save(issueModel);
    return issueModel;
  }

  public int checkReaderForBorrowedBooks(long id) {
    return issueRepository.readerHasBorrowedBooksCheck(id);
  }

  public List<IssueModel> getAllIssuesFromService() {
    return issueRepository.getIssues();
  }

  public List<IssueModel> getIssuesByReadersIdFromService(long id) {
    return issueRepository.getListOfIssuesByReadersId(id);
  }

  public List<BookModel> getAllBooksByReadersId(long id) {
    return issueRepository.getBookIdsByReadersId(id);
  }

}
