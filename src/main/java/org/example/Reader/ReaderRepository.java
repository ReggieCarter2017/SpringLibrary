package org.example.Reader;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final List<ReaderModel> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new ReaderModel("Игорь")
    ));
  }

  public ReaderModel getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void addReader(String name) {
    readers.add(new ReaderModel(name));
  }

  public void deleteReader(long id) {
    readers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().map(readers::remove);
  }

  public List<ReaderModel> getAllReaderModels() {
    return readers;
  }
}
