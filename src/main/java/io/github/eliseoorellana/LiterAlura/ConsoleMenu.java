package io.github.eliseoorellana.LiterAlura;

import java.util.List;
import java.util.Scanner;

import io.github.eliseoorellana.LiterAlura.dto.BookDTO;
import io.github.eliseoorellana.LiterAlura.service.BookService;

public class ConsoleMenu {


    private final BookService bookService;
    private final Scanner scanner;

    public ConsoleMenu() {
        this.bookService = new BookService(); // Dependiendo de cómo esté configurada la inyección de dependencias, puede ser necesario modificar esta línea
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("------ Elija la opción a través de su número ------");
            System.out.println("1.- Buscar libro por título");
            System.out.println("2.- Listar libros registrados");
            System.out.println("3.- Listar autores registrados");
            System.out.println("4.- Listar autores vivos en un determinado año");
            System.out.println("5.- Listar libros por idioma");
            System.out.println("0.- Salir");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Ingrese el nombre del libro que desea buscar:");
                    String title = scanner.nextLine();
                    BookDTO bookDTO = bookService.searchBookByTitle(title);
                    if (bookDTO != null) {
                        System.out.println("Libro encontrado y guardado en la base de datos:");
                        System.out.println(bookDTO);
                    }
                    break;
                case 2:
                    List<BookDTO> books = bookService.listAllBooks();
                    books.forEach(System.out::println);
                    break;
                case 3:
                    List<String> authors = bookService.listAllAuthors();
                    authors.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Ingrese el año para listar autores vivos:");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    List<String> authorsAlive = bookService.listAuthorsAliveInYear(year);
                    authorsAlive.forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Ingrese el idioma para listar libros (ES, EN, FR, PT):");
                    String language = scanner.nextLine();
                    List<BookDTO> booksByLanguage = bookService.listBooksByLanguage(language);
                    booksByLanguage.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
                }
        } while (option != 0);
    }
}