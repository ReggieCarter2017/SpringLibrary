package org.example.Book;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookModel {
  public static long sequence = 1L;
  private final long id;
  private final String name;
  public BookModel(String name) {
    this(sequence++, name);
  }

}
