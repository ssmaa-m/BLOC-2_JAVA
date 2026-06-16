package services;

import domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repositories.LibraryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    LibraryRepository repository;
    LibraryService libraryService;
    Book book;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(LibraryRepository.class);
        libraryService = new LibraryService(repository);
        book = Mockito.mock(Book.class);
    }

    @Test
    void addBookTC1() {
        Mockito.when(book.getTitle()).thenReturn("Harry potter");
        Mockito.when(book.getAuthor()).thenReturn(null);

        assertThrows(IllegalArgumentException.class , () -> libraryService.addBook(book));
    }

    @Test
    void addBookTC2() {
        Mockito.when(book.getTitle()).thenReturn("2026");
        Mockito.when(book.getAuthor()).thenReturn("Asma");

        List<Book> livreExistant = List.of(
                new Book("Harry potter" , "Hajar"),
                new Book("Prison break" , "Selma")
        );

        Mockito.when(repository.findAllBooks()).thenReturn(livreExistant);

        libraryService.addBook(book);

        Mockito.verify(repository , Mockito.times(1)).addAuthor("Asma");
        Mockito.verify(repository , Mockito.times(1)).addBook(book);
    }

    @Test
    void addBookTC3() {
        Mockito.when(book.getTitle()).thenReturn("2026");
        Mockito.when(book.getAuthor()).thenReturn("Asma");

        List<Book> livreExistant = List.of(
                new Book("Harry potter" , "Hajar"),
                new Book("Prison break" , "Selma"),
                new Book("Fall in love" , "Asma")
        );

        Mockito.when(repository.findAllBooks()).thenReturn(livreExistant);

        libraryService.addBook(book);

        Mockito.verify(repository , Mockito.never()).addAuthor("Asma");
        Mockito.verify(repository , Mockito.times(1)).addBook(book);
    }
}