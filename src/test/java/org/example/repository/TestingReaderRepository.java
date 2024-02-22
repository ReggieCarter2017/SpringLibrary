package org.example.repository;

import org.example.models.ReaderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingReaderRepository extends JpaRepository<ReaderModel, Long> {
}
