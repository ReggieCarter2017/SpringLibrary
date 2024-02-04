package org.example.Reader;

import org.example.Issue.IssueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReaderRepo extends JpaRepository<ReaderModel, Long> {

}
