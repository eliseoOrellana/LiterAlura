package io.github.eliseoorellana.LiterAlura.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String language;
    private int download_count;
}
