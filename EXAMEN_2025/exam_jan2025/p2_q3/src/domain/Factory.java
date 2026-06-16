package domain;

public class Factory {

    public Book CreateBook (String title , String auteur){
        return new BookImpl(title , auteur);
    }
}
