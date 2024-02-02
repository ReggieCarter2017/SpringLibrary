package org.example.Book;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

  private final List<BookModel> books;

  public BookRepository() {
    this.books = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    books.addAll(List.of(
      new BookModel("война и мир"),
      new BookModel("метрвые души"),
      new BookModel("чистый код")
    ));
  }

  public BookModel getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void addBook(String name) {
    books.add(new BookModel(name));
  }

  public void deleteBook(long id) {
    books.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().map(books::remove);
  }

  public List<BookModel> getAllBooks() {
    return books;
  }

}
