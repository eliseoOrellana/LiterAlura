package io.github.eliseoorellana.LiterAlura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.eliseoorellana.LiterAlura.dto.BookDTO;
import io.github.eliseoorellana.LiterAlura.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    //  @Autowired
    // private BookService bookService;

    // @GetMapping("/search")
    // public List<BookDTO> searchBooks(@RequestParam String title) {
    //     return bookService.searchBookByTitle(title);
        
    // }

    // @PostMapping("/add")
    // public BookDTO addBook(@RequestBody BookDTO bookDTO) {
    //     return bookService.saveBook(bookDTO);
    // }

    // @GetMapping
    // public List<BookDTO> listAllBooks() {
    //     return bookService.listAllBooks();
    // }

    // @GetMapping("/language")
    // public List<BookDTO> listBooksByLanguage(@RequestParam String language) {
    //     return bookService.listBooksByLanguage(language);
    // }
}
