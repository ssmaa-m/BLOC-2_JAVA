package main;

import domaine.Employe;
import domaine.Genre;

import java.util.function.Predicate;

public class PredicateGenreHomme implements Predicate<Employe> {

    @Override
    public boolean test(Employe e){
        return e.getGenre() == Genre.HOMME;
    }
}
