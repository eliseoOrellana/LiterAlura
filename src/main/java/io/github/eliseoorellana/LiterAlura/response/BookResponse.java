package io.github.eliseoorellana.LiterAlura.response;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {

    private List<BookDTO> results;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookDTO {
        private String title;
        private List<Author> authors;
        private List<String> languages;
        private int download_count;
        private String birth_year;
        private String death_year;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Author {
            private String name;
            private String birth_year;
            private String death_year;
        }
    }
}
