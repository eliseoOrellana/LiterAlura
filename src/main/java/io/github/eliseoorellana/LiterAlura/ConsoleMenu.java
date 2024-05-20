package io.github.eliseoorellana.LiterAlura;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.eliseoorellana.LiterAlura.dto.BookDTO;
import io.github.eliseoorellana.LiterAlura.service.BookService;

@Component
public class ConsoleMenu {

    @Autowired
    private BookService bookService;

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("------Elija la opción a través de su número------");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("0. Salir");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    System.out.println("Ingrese el nombre del libro que desea buscar:");
                    String title = scanner.nextLine();
                    BookDTO bookDTO = bookService.searchBookByTitle(title);
                    if (bookDTO != null) {
                        System.out.println("Libro encontrado y guardado en la base de datos.");
                    }
                    break;
                case 2:
                    List<BookDTO> books = bookService.listAllBooks();
                    books.forEach(book -> {
                        System.out.println("Título: " + book.getTitle());
                        System.out.println("Autor: " + book.getAuthor());
                        System.out.println("Idioma: " + book.getLanguage());
                        System.out.println("Descargas: " + book.getDownload_count());
                        System.out.println("--------------------");
                    });
                    break;
                case 3:
                    List<BookDTO> allBooks = bookService.listAllBooks(); // Renombrar a allBooks
                    if (allBooks.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        System.out.println("Detalles de los autores:\n");
                        allBooks.stream()
                                .collect(Collectors.groupingBy(BookDTO::getAuthor))
                                .forEach((author, booksByAuthor) -> {
                                    booksByAuthor.forEach(book -> {
                                        System.out.println("  Autor: " + book.getAuthor());
                                        System.out.println("  Título del libro: " + book.getTitle());
                                        System.out.println("  Fecha de nacimiento del autor: " + book.getBirth_year());
                                        System.out
                                                .println("  Fecha de fallecimiento del autor: " + book.getDeath_year());
                                        System.out
                                                .println("-----------------------------------------------------------");
                                    });
                                });
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Map<String, List<BookDTO>> authorsDetailsMap = bookService.listAuthorsDetailsAliveInYear(year);

                    if (authorsDetailsMap.isEmpty()) {
                        System.out.println("No hay autores vivos en el año especificado.");
                    } else {
                        System.out.println("Detalles de los autores vivos en el año " + year + ":\n");
                        authorsDetailsMap.forEach((author, booksByAuthor) -> {
                            System.out.println("  Autor: " + author);
                            booksByAuthor.forEach(book -> {
                                System.out.println("  Título del libro: " + book.getTitle());
                                System.out.println("  Fecha de nacimiento del autor: " + book.getBirth_year());
                                System.out.println("  Fecha de fallecimiento del autor: " + book.getDeath_year());
                                System.out.println("-----------------------------------------------------------");
                            });
                        });
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el idioma (ES, EN, FR, PT):");
                    String language = scanner.nextLine();
                    List<BookDTO> booksByLanguage = bookService.listBooksByLanguage(language);
                    booksByLanguage.forEach(book -> {
                        System.out.println("Título: " + book.getTitle());
                        System.out.println("Autor: " + book.getAuthor());
                        System.out.println("Idioma: " + book.getLanguage());
                        System.out.println("Descargas: " + book.getDownload_count());
                        System.out.println("--------------------");
                    });
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }
}