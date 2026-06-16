import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class GestionnaireProjet {


    /**
     * Filtre les tâches d'un projet en fonction de leur état et retourne leurs titres.
     *
     * @param p    le projet contenant les tâches à filtrer.
     * @param etat l'état des tâches à inclure dans le résultat.
     * @return une liste des titres des tâches correspondant à l'état spécifié.
     */
    public static List<String> filtrerTachesParEtat(Projet p, EtatTache etat) {
        return p
                .getTaches()
                .stream()
                .filter(t -> t.getEtat().equals(etat))
                .map(Tache::getTitre)
                .toList();
    }

    /**
     * Calcule la charge de travail totale (en heures) pour les tâches d'un projet ayant une priorité donnée.
     *
     * @param p        le projet contenant les tâches.
     * @param priorite la priorité des tâches dont la charge doit être calculée.
     * @return le total des heures estimées pour les tâches ayant la priorité spécifiée.
     */
    public static int calculerChargeTravailParPriorite(Projet p, Priorite priorite) {
        return p
                .getTaches()
                .stream()
                .filter(t -> t.getPriorite().equals(priorite))
                .mapToInt(Tache::getHeuresEstimees)
                .sum();
    }

    /**
     * Renvoie la liste des noms des développeurs impliqués dans un projet. Chaque développeur est
     * inclus une seule fois, même s'il est assigné à plusieurs tâches.
     *
     * @param p le projet contenant les tâches.
     * @return une liste des noms des développeurs impliqués dans le projet.
     */
    public static List<String> obtenirDeveloppeursImpliques(Projet p) {
        return p
                .getTaches()
                .stream()
                .map(Tache::getDeveloppeur)
                .map(Developpeur::getNom)
                .distinct()
                .toList();
    }

    /**
     * Calcule le nombre total d'heures estimées pour toutes les tâches d'un projet en utilisant `reduce`.
     *
     * @param p le projet contenant les tâches.
     * @return le total des heures estimées pour toutes les tâches du projet.
     */
    public static int calculerTotalHeuresEstimees(Projet p) {
        return p
                .getTaches()
                .stream()
                .mapToInt(Tache::getHeuresEstimees)
                .reduce(Integer::sum)
                .getAsInt();
    }

    /**
     * Regroupe les tâches d'un projet par état et calcule la charge totale de travail pour chaque état.
     *
     * @param p le projet contenant les tâches.
     * @return une map où les clés sont les états des tâches et les valeurs sont le total des heures estimées pour chaque état.
     */
    public static Map<EtatTache, Integer> regrouperChargeParEtat(Projet p) {
        return p
                .getTaches()
                .stream()
                .collect(Collectors.groupingBy(Tache::getEtat , Collectors.summingInt(Tache::getHeuresEstimees)));
    }

}
