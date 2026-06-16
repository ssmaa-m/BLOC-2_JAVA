package repositories;


import domain.Book;
import domain.Factory;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {
    private final static int SLEEP_TIME = 500;

    private List<String> authors = new ArrayList<>(List.of("George Orwell", "Aldous Huxley"));

    private List<String> books = new ArrayList<>(List.of("1984, George Orwell",
            "Brave New World, Aldous Huxley"));

    private Factory factory;

    public LibraryRepository(Factory factory) {
        this.factory = factory;
    }

    public List<Book> findAllBooks() {
        // Simule l'accès à une base de données ou une autre source de données


        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return parseBooks();
    }

    /**
     * Déserialiser tous les livres (de la fake DB)
     * et les transformer en objets BookImpl
     */
    private List<Book> parseBooks() {
        return books.stream()
                .map(book -> {
                    String[] parts = book.split(",");
                    return factory.CreateBook(parts[0].trim(), parts[1].trim());
                })
                .toList();

    }

    public List<String> getAuthors() {
        // Simule l'accès à une base de données ou une autre source de données


        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    public String addAuthor(String author) {
        // Simule l'accès à une base de données ou une autre source de données

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        authors.add(author);
        return author;
    }

    public Book addBook(Book book) {
        // Simule l'accès à une base de données ou une autre source de données

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        books.add(serializeBook(book));
        return book;
    }

    /**
     * Sérialiser un livre pour le stocker dans la fake database
     */
    private String serializeBook(Book book) {
        return book.getTitle() + ", " + book.getAuthor();
    }

}

