public class Tache {
    private String titre;
    private Developpeur developpeur;
    private Priorite priorite;
    private EtatTache etat;
    private int heuresEstimees;

    public Tache(String titre, Developpeur developpeur, Priorite priorite, EtatTache etat, int heuresEstimees) {
        this.titre = titre;
        this.developpeur = developpeur;
        this.priorite = priorite;
        this.etat = etat;
        this.heuresEstimees = heuresEstimees;
    }

    public String getTitre() {
        return titre;
    }

    public Developpeur getDeveloppeur() {
        return developpeur;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public EtatTache getEtat() {
        return etat;
    }

    public int getHeuresEstimees() {
        return heuresEstimees;
    }

    public void setEtat(EtatTache etat) {
        this.etat = etat;
    }
}
