package services;

import domain.Book;
import repositories.LibraryRepository;

import java.util.List;

public class LibraryService {
    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("repository cannot be null");
        }
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAllBooks();
    }

    public List<String> getAllAuthors() {
        return repository.getAuthors();
    }

    // Add a book to the library and add the author only if the author is not already in the library
    public Book addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book cannot be null");
        }
        if (book.getAuthor() == null) {
            throw new IllegalArgumentException("book author cannot be null");
        }
        if (book.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("book author name cannot be empty");
        }
        if (book.getTitle() == null) {
            throw new IllegalArgumentException("book title cannot be null");
        }
        if (book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("book title cannot be empty");
        }

        if (getAllBooks().stream().anyMatch(b -> b.getTitle().equals(book.getTitle()) && b.getAuthor().equals(book.getAuthor()))) {
            throw new IllegalArgumentException("book already exists");
        }
        if (getAllBooks().stream().noneMatch(b -> b.getAuthor().equals(book.getAuthor()))) {
            repository.addAuthor(book.getAuthor());
        }
        return repository.addBook(book);
    }
}