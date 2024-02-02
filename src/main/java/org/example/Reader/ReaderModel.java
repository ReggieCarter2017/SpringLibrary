package org.example.Reader;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReaderModel {
  public static long sequence = 1L;
  private final long id;
  private final String name;
  public ReaderModel(String name) {
    this(sequence++, name);
  }

}
