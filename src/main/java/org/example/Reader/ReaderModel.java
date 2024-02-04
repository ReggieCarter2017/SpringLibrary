package org.example.Reader;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "readers")
@NoArgsConstructor
public class ReaderModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;
  public ReaderModel(String name) {
    this.name = name;
  }

}
