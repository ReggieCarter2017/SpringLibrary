package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
public class IssueModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "bookId")
  private Long bookId;
  @Column(name = "readerId")
  private Long readerId;
  @Column(name = "issuedAt")
  private LocalDateTime issuedAt;
  @Column(name = "returnedAt")
  private LocalDateTime returnedAt;


  public void setReturnedAt() {
    this.returnedAt = LocalDateTime.now();
  }

  public IssueModel(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = LocalDateTime.now();
    this.returnedAt = null;
  }

}
