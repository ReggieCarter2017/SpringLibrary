package org.example.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {

  /**
   * Идентификатор читателя
   */
  @NotNull
  @Min(0)
  @Max(100)
  private Long readerId;

  /**
   * Идентификатор книги
   */
  private Long bookId;

}
