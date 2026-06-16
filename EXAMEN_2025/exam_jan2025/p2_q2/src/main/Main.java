package main;

import domain.BookImpl;
import repositories.LibraryRepository;
import services.LibraryService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        LibraryRepository libraryRepository = new LibraryRepository();
        LibraryService libraryService = new LibraryService(libraryRepository);
        List<Book> books = List.of(new Book("Why I Write", "George Orwell"),
                new Book("Island", "Aldous Huxley"),
                new Book("The Catcher in the Rye", "J.D. Salinger"),
                new Book("To Kill a Mockingbird", "Harper Lee"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        System.out.println("""
                Ajout asynchrone de 5 nouveaux livres
                avec affichage de chaque nouveau livre juste après son ajout 
                tout en utilisant les completable futures.
                """);
        long start = System.currentTimeMillis();

        System.out.println("""
                Votre résultat doit être similaire à celui-ci (l'ordre n'a pas d'importance):
                Nouveau livre ajouté: Book{title='Why I Write', author='George Orwell'}
                Nouveau livre ajouté: Book{title='Island', author='Aldous Huxley'}
                Nouveau livre ajouté: Book{title='The Catcher in the Rye', author='J.D. Salinger'}
                Nouveau livre ajouté: Book{title='To Kill a Mockingbird', author='Harper Lee'}
                Nouveau livre ajouté: Book{title='The Great Gatsby', author='F. Scott Fitzgerald'}
                """);


        System.out.println("Votre résultat :");

        //TODO : Veuillez remplacer les deux lignes de code qui suivent (ajouts synchrones et affichage des livres)
        //  par la nouvelle méthode asynchrone addBookAsync
        //  que vous avez ajouté à LibraryService.
        //  Vous devez ajouter de manière asynchrone 5 nouveaux livres
        //  et afficher chaque nouveau livre juste après son ajout
        //  tout en utilisant les completable futures.
        //  NB : Attention donc à ne pas attendre que tous les livres soit ajoutés avant d’afficher les livres ajoutés,
        //  l’affichage des livres doit se faire au fur et à mesure des ajouts.


        books.forEach(book -> {
            libraryService.addBookAsync(book).thenAccept(b -> System.out.println("Nouveau livre ajouté: " + b));
        });


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new Exception();
        }


        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + " ms");
        System.out.println("Vous devriez avoir un temps d'exécution d'environ 2050 ms");

    }
}
