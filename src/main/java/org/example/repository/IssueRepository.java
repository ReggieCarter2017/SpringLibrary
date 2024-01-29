package org.example.repository;

import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {
  private List<Issue> issues;
  @Autowired
  private BookRepository bookRepository;
  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    issues.add(issue);
  }

  public Issue getIssueById(long id) {
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

  public List<Issue> getListOfIssuesByReadersId(long id) {
    return issues.stream().filter(it -> Objects.equals(id, it.getReaderId())).toList();
  }

  public void returnIssue(long id) {
    issues.stream().filter(it -> Objects.equals(id, it.getId())).forEach(it -> it.setReturnedAt());
  }

  public List<Issue> getIssues() {
    return issues;
  }
}
