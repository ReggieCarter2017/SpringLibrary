package org.example.repos;

import org.example.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel, Long> {
}
