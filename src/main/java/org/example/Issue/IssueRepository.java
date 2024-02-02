package org.example.Issue;

import org.example.Book.BookModel;
import org.example.Book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {
  private List<IssueModel> issues;
  @Autowired
  private BookRepository bookRepository;
  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(IssueModel issueModel) {
    issues.add(issueModel);
  }

  public IssueModel getIssueById(long id) {
    return issues.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().orElse(null);
  }

  public int readerHasBorrowedBooksCheck(long id) {
    if ((issues.stream().filter(it -> Objects.equals(it.getReaderId(), id)).findFirst().orElse(null)) == null) {
      // 1 - the reader has borrowed a book
      return 1;
    } else {
      // 2 - the reader has not borrowed a single book
      return 2;
    }
  }

  public List<IssueModel> getListOfIssuesByReadersId(long id) {
    return issues.stream().filter(it -> Objects.equals(id, it.getReaderId())).toList();
  }

  public void returnIssue(long id) {
    issues.stream().filter(it -> Objects.equals(id, it.getId())).forEach(it -> it.setReturnedAt());
  }

  public List<IssueModel> getIssues() {
    return issues;
  }

  public List<BookModel> getBookIdsByReadersId(long id) {
    return issues.stream().filter(it -> Objects.equals(it.getReaderId(), id))
            .map(it -> bookRepository.getBookById(it.getBookId())).toList();
  }
}
