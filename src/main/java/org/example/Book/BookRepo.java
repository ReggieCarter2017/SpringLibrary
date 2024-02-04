package org.example.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel, Long> {
}
