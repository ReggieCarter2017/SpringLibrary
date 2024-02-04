package org.example.Book;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
public class BookModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;

  public BookModel(String name) {
    this.name = name;
  }



}
