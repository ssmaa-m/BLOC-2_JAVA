package main;

import domain.Book;
import domain.Factory;
import repositories.LibraryRepository;
import services.LibraryService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO : Veuillez créer une factory pour la création de livres, l'injecter et l'utiliser
        //  là où c'est nécessaire.

        Factory factory = new Factory();
        LibraryRepository libraryRepository = new LibraryRepository(factory);
        LibraryService libraryService = new LibraryService(libraryRepository);
        List<Book> books =List.of(
                factory.CreateBook("Why I Write", "George Orwell"),
                factory.CreateBook("Island", "Aldous Huxley"),
                factory.CreateBook("The Catcher in the Rye", "J.D. Salinger"),
                factory.CreateBook("To Kill a Mockingbird", "Harper Lee"),
                factory.CreateBook("The Great Gatsby", "F. Scott Fitzgerald"));

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
