package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Issue;
import org.example.repository.BookRepository;
import org.example.repository.IssueRepository;
import org.example.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private BookRepository bookRepository;
    private IssueRepository issueRepository;
    private ReaderRepository readerRepository;

}
