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

    public enum Type {
        ENTREE("Entrée") , PLAT("Plat") , DESSERT("Dessert");

        private String nom;

        Type(String nom) {
            this.nom = nom;
        }

        public String getNom() {
            return nom;
        }
    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0) ;
    private Type type;

    private List<Instruction> instructions;
    private Map<Ingredient , IngredientQuantifie> ingredients;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout , Type type) {
        Util.checkString(nom);
        Util.checkPositiveOrNul(nbPersonnes);
        Util.checkObject(cout);
        Util.checkObject(niveauDeDifficulte);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.type = type;
        this.instructions = new ArrayList<>();
        this.ingredients = new HashMap<>();
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

    public Type getType() {
        return type;
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

    public boolean ajouterIngredient (Ingredient ingredient , int quantite , Unite unite){
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient , quantite , unite);
        ingredients.put(ingredientQuantifie.getIngredient() , ingredientQuantifie);
        return true;
    }

    public boolean ajouterIngredient (Ingredient ingredient , int quantite){
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient , quantite , Unite.NEANT);
        ingredients.put(ingredientQuantifie.getIngredient() , ingredientQuantifie);
        return true;
    }

    public boolean modifierIngredient (Ingredient ingredient , int quantite , Unite unite){
        for (IngredientQuantifie ingredientQuantifie: ingredients.values()) {
            if(ingredientQuantifie.getIngredient() == ingredient){
                ingredientQuantifie.setQuantite(quantite);
                ingredientQuantifie.setUnite(unite);
                return true;
            }
        }
        return false;
    }

    public boolean supprimerIngredient(Ingredient ingredient) {
        return ingredients.remove(ingredient) != null;
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        for (IngredientQuantifie ingredientQuantifie : ingredients.values()) {
            if(ingredientQuantifie.getIngredient() == ingredient){
                return ingredientQuantifie;
            }
        }
        return null;
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
        for (IngredientQuantifie ing : this.ingredients.values()) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.instructions) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }


}
