package io.github.eliseoorellana.LiterAlura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.eliseoorellana.LiterAlura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>  {

}
