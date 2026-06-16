package domaine;


import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {

    public enum Difficulte {
        X , XX , XXX , XXXX , XXXXX;

        @Override
        public String toString () {
            return super.toString().replace("X" , "*");
        }
    }

    public enum Cout {
        $ , $$ , $$$ , $$$$ , $$$$$;

        @Override
        public String toString() {
            return super.toString().replace("$" , "€");
        }
    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0) ;

    private List<Instruction> instructions;
    private Set<IngredientQuantifie> ingredients;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        Util.checkString(nom);
        Util.checkPositiveOrNul(nbPersonnes);
        Util.checkObject(cout);
        Util.checkObject(niveauDeDifficulte);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.instructions = new ArrayList<>();
        this.ingredients = new HashSet<>();
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void recalculterDuree() {
        Duration total = Duration.ZERO;
        for (Instruction instruction : instructions) {
            total = total.plus(instruction.getDureeEnMinutes());
        }

        this.dureeEnMinutes = total;
    }

    public void insererInstruction (int position , Instruction instruction){
        int positionn = position - 1;
        Util.checkPositiveOrNul(positionn);

        if(positionn >= instructions.size()){
            throw new IllegalArgumentException();
        }
        instructions.add(positionn , instruction);
        recalculterDuree();
    }

    public void ajouterInstruction (Instruction instruction){
        instructions.add(instruction);
        recalculterDuree();
    }

    public Instruction remplacerInstruction (int position , Instruction instruction) {
        int positionn = position - 1;
        Util.checkPositiveOrNul(positionn);

        if(positionn >= instructions.size()){
            throw new IllegalArgumentException();
        }

        Instruction instructionRemplacer = instructions.get(positionn);
        instructions.set(positionn , instruction);
        recalculterDuree();
        return instructionRemplacer;
    }

    public Instruction supprimerInstruction (int position) {
        int positionn = position - 1;
        Instruction instructionSupprimer = instructions.get(positionn);
        instructions.remove(positionn);
        recalculterDuree();
        return instructionSupprimer;
    }

    public List<Instruction> instructions () {
        return Collections.unmodifiableList(instructions);
    }

    // renvoie l’ensemble des ingrédients, utilisés dans le plat, triés par nom
    //d’ingrédient.
    public SortedSet<Ingredient> ingredients(){
        SortedSet<Ingredient> ingredientsNom = new TreeSet<>(Comparator.comparing(Ingredient::getNom));
        for (IngredientQuantifie ingredient : ingredients) {
            ingredientsNom.add(ingredient.getIngredient());
        }
        return ingredientsNom;
    }

    // version avec class une class anonyme Comparator
    public SortedSet<Ingredient> ingredientsNom(){
        SortedSet<Ingredient> ingredients1 = new TreeSet<>(new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });

        for (IngredientQuantifie ingredient : ingredients) {
            ingredients1.add(ingredient.getIngredient());
        }

        return ingredients1;
    }


    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        //for (IngredientQuantifie ing : this.ingredients) {
        //    res += ing + "\n";
        //}
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.instructions) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }


}
