package io.github.eliseoorellana.LiterAlura.mapper;

import org.springframework.stereotype.Component;

import io.github.eliseoorellana.LiterAlura.dto.BookDTO;
import io.github.eliseoorellana.LiterAlura.model.Book;

@Component
public class BookMapper {
    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setLanguage(book.getLanguage());
        dto.setDownload_count(book.getDownload_count());
        return dto;
    }

    public Book toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setLanguage(dto.getLanguage());
        book.setDownload_count(dto.getDownload_count());
        return book;
    }

}
