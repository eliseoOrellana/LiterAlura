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

        List<Book> existingBooks = bookRepository.findByTitleContaining(title);
    if (!existingBooks.isEmpty()) {
        System.out.println("El libro ya existe en la base de datos.");
        return null;
        // return bookMapper.toDTO(existingBooks.get(0)); // Devolver el primer libro encontrado
    }
      
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + title.replace(" ", "%20");
        BookResponse response = restTemplate.getForObject(url, BookResponse.class);

        if (response == null || response.getResults().isEmpty()) {
            System.out.println("El libro no fue encontrado.");
            return null;
        }

        // Obtener el primer libro de la lista de resultados
        BookResponse.BookDTO bookDTO = response.getResults().get(0);

        // Crear y devolver un DTO con los datos del libro
        BookDTO newBookDTO = new BookDTO();
        newBookDTO.setTitle(bookDTO.getTitle());
        newBookDTO.setAuthor(bookDTO.getAuthors().get(0).getName());
        newBookDTO.setLanguage(bookDTO.getLanguages().get(0));
        newBookDTO.setDownload_count(bookDTO.getDownload_count());
        
        if (bookDTO.getAuthors().size() > 0) {
            newBookDTO.setBirth_year(bookDTO.getAuthors().get(0).getBirth_year());
            newBookDTO.setDeath_year(bookDTO.getAuthors().get(0).getDeath_year());
        }

        // Mostrar los detalles del libro por consola
        System.out.println("Título: " + newBookDTO.getTitle());
        System.out.println("Autor: " + newBookDTO.getAuthor());
        System.out.println("Idioma: " + newBookDTO.getLanguage());
        System.out.println("Descargas: " + newBookDTO.getDownload_count());

        // Guardar el libro en la base de datos
        Book book = bookMapper.toEntity(newBookDTO);
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
        return bookRepository.findAll().stream()
        .map(Book::getAuthor)
        .distinct()
        .collect(Collectors.toList()); // Aquí estamos devolviendo todos los autores únicos, no filtramos por año de vida porque no tenemos esa información
}


    public List<BookDTO> listBooksByLanguage(String language) {
        List<Book> books = bookRepository.findByLanguage(language);
        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }
}
