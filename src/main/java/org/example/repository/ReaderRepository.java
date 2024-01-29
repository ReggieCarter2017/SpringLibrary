package org.example.repository;

import jakarta.annotation.PostConstruct;
import org.example.model.Reader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new org.example.model.Reader("Игорь")
    ));
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void addReader(String name) {
    readers.add(new Reader(name));
  }

  public void deleteReader(long id) {
    readers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().map(readers::remove);
  }
}
