import java.util.*;
import java.util.stream.Collectors;

public class Projet {
    private List<Tache> taches;
    private List<String> competencesRequises;

    public Projet(List<String> competencesRequises) {
        this.taches = new ArrayList<>();
        this.competencesRequises = competencesRequises;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public List<String> getCompetencesRequises() {
        return competencesRequises;
    }

    public void setCompetencesRequises(List<String> competencesRequises) {
        this.competencesRequises = competencesRequises;
    }

    public void ajouterTache(Tache tache) {
        // Vérifie si la tâche est déjà dans la liste avant de l'ajouter
        if (!taches.contains(tache)) {
            taches.add(tache);
        }
    }

    public void retirerTache(Tache tache) {
        taches.remove(tache);
    }

    /**
     * Retourne la liste des tâches assignées à un développeur spécifique.
     *
     * @param nomDeveloppeur le nom du développeur pour lequel rechercher les tâches.
     * @return une liste de {@link Tache} assignées au développeur spécifié.
     *         La liste est vide si aucune tâche n'est assignée à ce développeur.
     */
    public List<Tache> obtenirTachesParDeveloppeur(String nomDeveloppeur) {
        List<Tache> resultat = new ArrayList<>();

        for (Tache tache : taches) {
            if(tache.getDeveloppeur().getNom().equals(nomDeveloppeur)){
                resultat.add(tache);
            }
        }

        return resultat;
    }

    /**
     * Génère une correspondance entre les compétences requises par le projet et
     * les développeurs ayant ces compétences.
     *
     * @return une map où chaque clé est une compétence (String) et chaque valeur
     *         est une liste de noms de développeurs (List&lt;String&gt;) ayant cette compétence.
     *         Si aucun développeur n'a une compétence donnée, la valeur associée est une liste vide.
     */
    public Map<String, List<String>> obtenirDeveloppeursParCompetence() {
        Map<String , List<String>> resultat = new HashMap<>();

        for (String competence : competencesRequises) {
            List<String> nom = new ArrayList<>();

            for (Tache tache : taches) {
                if(tache.getDeveloppeur().getCompetences().contains(competence)){
                    String nomN = tache.getDeveloppeur().getNom();
                    if(!nom.contains(nomN)){
                        nom.add(tache.getDeveloppeur().getNom());
                    }
                }
            }
            resultat.put(competence , nom);
        }
        return resultat;
    }

}
