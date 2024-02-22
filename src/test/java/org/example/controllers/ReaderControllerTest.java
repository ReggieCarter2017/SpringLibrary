package org.example.controllers;

import lombok.Data;
import org.example.models.IssueModel;
import org.example.models.ReaderModel;
import org.example.repos.IssueRepo;
import org.example.repos.ReaderRepo;
import org.example.services.IssuerService;
import org.example.services.ReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.Reader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class ReaderControllerTest extends ControllersTest {

    @Autowired
    private ReaderRepo readerRepo;
    @Autowired
    private WebTestClient webTest;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IssueRepo issueRepo;
    @Autowired
    private IssuerService issuerService;
    @Autowired
    private ReaderService readerService;


    @Data
    static class TestingReaderResponse {
        private Long id;
        private String name;
    }

    @Data
    static class TestingIssueResponse {
        private Long readerId;
        private Long bookId;
    }

    @Test
    void showReaderById() {
        ReaderModel expected = readerRepo.save(new ReaderModel("John"));
        TestingReaderResponse response = webTest.get()
                .uri("/readers/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(TestingReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expected.getId(), response.getId());
        Assertions.assertEquals(expected.getName(), response.getName());
    }

    @Test
    void deleteReader() {
        ReaderModel readerModel = readerRepo.save(new ReaderModel("Luke"));

        webTest.delete()
                .uri("/readers/{id}", readerModel.getId())
                .exchange()
                .expectStatus().isOk();

        Optional<ReaderModel> deletedReader = readerRepo.findById(readerModel.getId());
        Assertions.assertFalse(deletedReader.isPresent());
    }

    @Test
    void getAllReaders() {
        List<ReaderModel> expected = readerRepo.saveAll(List.of(
                new ReaderModel("John"),
                new ReaderModel("Kevin"),
                new ReaderModel("Lucy")
        ));

        List<TestingReaderResponse> testingReaderResponses = webTest.get()
                .uri("/readers")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<TestingReaderResponse>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), testingReaderResponses.size());
        for (TestingReaderResponse response : testingReaderResponses) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), response.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), response.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void showHisIssues() {
        Long readerId = 222L;

        List<IssueModel> issueModels = Arrays.asList(new IssueModel(1, readerId), new IssueModel(2, readerId));

        List<TestingIssueResponse> testingIssueResponses = webTest.get()
                .uri("/readers/{id}/issues", readerId) // Provide the reader ID here
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<TestingIssueResponse>>(){
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(issueModels.size(), testingIssueResponses.size());
        for (TestingIssueResponse issueResponse : testingIssueResponses) {
            boolean found = issueModels.stream()
                    .anyMatch(issueModel -> Objects.equals(issueModel.getReaderId(), issueResponse.getReaderId())
                            && Objects.equals(issueModel.getBookId(), issueResponse.getBookId()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void addReader() {
        String name = "Joshua";

        TestingReaderResponse response = webTest.post()
                .uri("/readers")
                .bodyValue(Collections.singletonMap("name", name))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TestingReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getName());
        Assertions.assertNotNull(response.getId());
        Assertions.assertTrue(readerRepo.findById(response.getId()).isPresent());
    }

    @BeforeEach
    void resetTable() {
        jdbcTemplate.execute("TRUNCATE TABLE readers");
        jdbcTemplate.execute("TRUNCATE TABLE issues");
    }
}