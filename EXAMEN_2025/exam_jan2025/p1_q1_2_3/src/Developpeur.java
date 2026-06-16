import java.util.List;

public class Developpeur {
    private String nom;
    private List<String> competences;

    public Developpeur(String nom, List<String> competences) {
        this.nom = nom;
        this.competences = competences;
    }

    public String getNom() {
        return nom;
    }

    public List<String> getCompetences() {
        return competences;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Developpeur that = (Developpeur) obj;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
