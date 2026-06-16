package domaine;

import java.util.Set;

public class StageStub implements Stage {

    private Sport sport;
    private  Moniteur moniteur;
    private int numeroSemaine;

    public StageStub(Sport sport, Moniteur moniteur, int numeroSemaine) {
        this.sport = sport;
        this.moniteur = moniteur;
        this.numeroSemaine = numeroSemaine;
    }

    @Override
    public String getIntitule() {
        return "";
    }

    @Override
    public String getLieu() {
        return "";
    }

    @Override
    public int getNumeroDeSemaine() {
        return numeroSemaine;
    }

    @Override
    public Sport getSport() {
        return sport;
    }

    @Override
    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return false;
    }

    @Override
    public boolean supprimerMoniteur() {
        return false;
    }

    @Override
    public Moniteur getMoniteur() {
        return moniteur;
    }

    @Override
    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean contientEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public int nombreDEnfants() {
        return 0;
    }

    @Override
    public Set<Enfant> enfants() {
        return Set.of();
    }
}
