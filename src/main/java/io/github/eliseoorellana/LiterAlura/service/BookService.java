package io.github.eliseoorellana.LiterAlura.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.eliseoorellana.LiterAlura.dto.BookDTO;
import io.github.eliseoorellana.LiterAlura.mapper.BookMapper;
import io.github.eliseoorellana.LiterAlura.model.Book;
import io.github.eliseoorellana.LiterAlura.repository.BookRepository;
import io.github.eliseoorellana.LiterAlura.response.BookResponse;

@Service
public class BookService {

    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

     private final String API_URL = "https://gutendex.com/books?search=";

    public BookDTO searchBookByTitle(String title) {
        if (bookRepository.existsByTitle(title)) {
            System.out.println("El libro ya existe en la base de datos.");
            return null;
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + title;
        BookResponse response = restTemplate.getForObject(url, BookResponse.class);

        if (response == null || response.getResults().isEmpty()) {
            System.out.println("El libro no fue encontrado.");
            return null;
        }

        BookResponse.BookDTO bookDTO = response.getResults().get(0);
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthors().get(0).getName());
        book.setLanguage(bookDTO.getLanguages().get(0));
        book.setDownloads(bookDTO.getDownloadCount());

        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }


    public List<BookDTO> listAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
            return Collections.emptyList(); // Devuelve una lista vacía
        }
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    public List<String> listAllAuthors() {
        return bookRepository.findAll().stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> listAuthorsAliveInYear(int year) {
        // Implementar lógica basada en los datos disponibles de la API o de la base de datos
        return List.of(); // Retorna una lista vacía temporalmente
    }

    public List<BookDTO> listBooksByLanguage(String language) {
        List<Book> books = bookRepository.findByLanguage(language);
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }
}
