package org.example.controllers;

import lombok.Data;
import org.example.models.BookModel;
import org.example.models.ReaderModel;
import org.example.repos.BookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest extends ControllersTest{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private WebTestClient webTest;

    @Data
    static class TestingBookResponse {
        private Long id;
        private String name;
    }

    @Test
    void showAllBooks() {
        List<BookModel> expected = bookRepo.saveAll(List.of(
                new BookModel("War and Peace"),
                new BookModel("Pygmalion"),
                new BookModel("Kitchen")
        ));

        List<TestingBookResponse> testingBookResponseList = webTest.get()
                .uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<TestingBookResponse>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), testingBookResponseList.size());
        for (TestingBookResponse response : testingBookResponseList) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), response.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), response.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void showBookById() {
        BookModel expected = bookRepo.save(new BookModel("War and peace"));
        TestingBookResponse response = webTest.get()
                .uri("/books/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(TestingBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expected.getId(), response.getId());
        Assertions.assertEquals(expected.getName(), response.getName());
    }

    @Test
    void addBook() {
    }

    @Test
    void deleteBook() {
    }
}