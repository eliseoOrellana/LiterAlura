package io.github.eliseoorellana.LiterAlura.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.eliseoorellana.LiterAlura.dto.BookDTO;
import io.github.eliseoorellana.LiterAlura.mapper.BookMapper;
import io.github.eliseoorellana.LiterAlura.model.Book;
import io.github.eliseoorellana.LiterAlura.repository.BookRepository;

@Service
public class BookService {

    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    public BookDTO saveBook(BookDTO bookDTO) {
        if (!bookRepository.existsByTitle(bookDTO.getTitle())) {
            Book book = bookMapper.toEntity(bookDTO);
            book = bookRepository.save(book);
            return bookMapper.toDTO(book);
        }
        return null; // o lanzar una excepción
    }

    public List<BookDTO> listAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    public List<BookDTO> listBooksByLanguage(String language) {
        List<Book> books = bookRepository.findByLanguage(language);
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }
}
