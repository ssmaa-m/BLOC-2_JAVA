package main;

import domaine.Employe;

import java.util.function.Function;

public class FonctionNomEmploye implements Function<Employe , String> {

    @Override
    public String apply (Employe e){
        return e.getNom();
    }

}
