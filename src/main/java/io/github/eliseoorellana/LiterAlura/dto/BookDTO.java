package io.github.eliseoorellana.LiterAlura.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String language;
    private int download_count;
    private String birth_year;
    private String death_year;
    private List<String> booksByAuthor; 
}

