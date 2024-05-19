package io.github.eliseoorellana.LiterAlura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.eliseoorellana.LiterAlura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>  {

    List<Book> findByTitleContaining(String title);
    List<Book> findByLanguage(String language);
    boolean existsByTitle(String title);
}
