package org.example.repos;

import org.example.models.ReaderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepo extends JpaRepository<ReaderModel, Long> {

}
