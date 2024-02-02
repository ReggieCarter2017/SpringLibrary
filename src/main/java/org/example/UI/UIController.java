package org.example.UI;

import org.example.Issue.IssuerService;
import org.example.Reader.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.Book.BookService;

@Controller
@RequestMapping("/ui")
public class UIController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private IssuerService issuerService;

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.getAllBooksFromService());
        return "books";
    }

    @GetMapping("/readers")
    public String readers(Model model) {
        model.addAttribute("readers", readerService.getAllReadersFromService());
        return "readers";
    }

    @GetMapping("/issues")
    public String issues(Model model) {
        model.addAttribute("issues", issuerService.getAllIssuesFromService());
        return "issues";
    }

    @GetMapping("/readers/{id}")
    public String readersBooks(@PathVariable long id, Model model) {
        model.addAttribute("readerModel", readerService.getReaderByIdFromService(id));
        model.addAttribute("readersbooks", issuerService.getAllBooksByReadersId(id));
        return "readerissues";
    }
}