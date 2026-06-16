package main;

import domain.Book;
import repositories.LibraryRepository;
import services.LibraryService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryRepository libraryRepository = new LibraryRepository();
        LibraryService libraryService = new LibraryService(libraryRepository);
        List<Book> books =List.of(new Book("Why I Write", "George Orwell"),
                new Book("Island", "Aldous Huxley"),
                new Book("The Catcher in the Rye", "J.D. Salinger"),
                new Book("To Kill a Mockingbird", "Harper Lee"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        System.out.println("Ajout synchrone de 5 nouveaux livres");
        long start = System.currentTimeMillis();
        books.forEach(libraryService::addBook);

        System.out.println("Liste de tous les livres dans la bibliothèque)");
        libraryService.getAllBooks().forEach(System.out::println);
        long end = System.currentTimeMillis();

        System.out.println("Liste de tous les auteurs dans la bibliothèque)");
        libraryService.getAllAuthors().forEach(System.out::println);
        System.out.println("Execution time: " + (end - start) + " ms");

    }
}
