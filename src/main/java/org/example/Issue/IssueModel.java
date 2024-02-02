package org.example.Issue;

import java.time.LocalDateTime;


public class IssueModel {
  public static long sequence = 1L;
  private final long id;
  private final long bookId;
  private final long readerId;
  private final LocalDateTime issuedAt;
  private LocalDateTime returnedAt;

  public long getId() {
    return id;
  }

  public long getBookId() {
    return bookId;
  }

  public long getReaderId() {
    return readerId;
  }

  public LocalDateTime getIssuedAt() {
    return issuedAt;
  }

  public LocalDateTime getReturnedAt() {
    return returnedAt;
  }

  public void setReturnedAt() {
    this.returnedAt = LocalDateTime.now();
  }

  public IssueModel(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = LocalDateTime.now();
    this.returnedAt = null;
  }

}
