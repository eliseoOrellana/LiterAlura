package io.github.eliseoorellana.LiterAlura;

import java.util.List;
import java.util.Scanner;

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
                    // Implementa la funcionalidad para listar autores registrados
                    break;
                case 4:
                    // Implementa la funcionalidad para listar autores vivos en un determinado año
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