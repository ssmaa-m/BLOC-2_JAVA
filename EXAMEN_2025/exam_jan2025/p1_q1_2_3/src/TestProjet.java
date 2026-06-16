import java.util.*;

public class TestProjet {
    public static void main(String[] args) {

        // Création de développeurs
        Developpeur dev1 = new Developpeur("Alice", Arrays.asList("Java", "SQL", "Python"));
        Developpeur dev2 = new Developpeur("Bob", Arrays.asList("C++", "Java", "HTML"));
        Developpeur dev3 = new Developpeur("Charlie", Arrays.asList("JavaScript", "Python", "React"));

        // Création de tâches (sans utiliser UrgenceTache et TypeTache)
        Tache t1 = new Tache("Créer l'API", dev1, Priorite.HAUTE, EtatTache.A_FAIRE, 10);
        Tache t2 = new Tache("Implémenter l'interface", dev2, Priorite.MOYENNE, EtatTache.EN_COURS, 15);
        Tache t3 = new Tache("Optimiser les requêtes SQL", dev1, Priorite.HAUTE, EtatTache.TERMINEE, 8);
        Tache t4 = new Tache("Développer la page d'accueil", dev3, Priorite.BASSE, EtatTache.BLOQUEE, 5);

        // Création d'un projet avec des compétences requises
        Projet projet = new Projet(Arrays.asList("Java", "SQL", "Python", "HTML"));

        // Ajout des tâches au projet
        projet.ajouterTache(t1);
        projet.ajouterTache(t2);
        projet.ajouterTache(t3);
        projet.ajouterTache(t4);

        // Test : Liste des tâches par développeur
        System.out.println("\n=== Test : Tâches par développeur ===");
        System.out.println("Tâches pour Alice (attendu : 2 tâches)");
        projet.obtenirTachesParDeveloppeur("Alice").forEach(t -> System.out.println("- " + t.getTitre()));
        // Résultat attendu :
        // - Créer l'API
        // - Optimiser les requêtes SQL

        System.out.println("\nTâches pour Bob (attendu : 1 tâche)");
        projet.obtenirTachesParDeveloppeur("Bob").forEach(t -> System.out.println("- " + t.getTitre()));
        // Résultat attendu :
        // - Implémenter l'interface

        System.out.println("\nTâches pour Charlie (attendu : 1 tâche)");
        projet.obtenirTachesParDeveloppeur("Charlie").forEach(t -> System.out.println("- " + t.getTitre()));
        // Résultat attendu :
        // - Développer la page d'accueil


        // Test : Développeurs disponibles pour chaque compétence
        System.out.println("\n=== Test : Développeurs disponibles pour chaque compétence ===");
        Map<String, List<String>> developpeursParCompetence = projet.obtenirDeveloppeursParCompetence();
        for (Map.Entry<String, List<String>> entry : developpeursParCompetence.entrySet()) {
            System.out.println("Compétence : " + entry.getKey() + " => Développeurs : " + entry.getValue());
        }
        // Résultat attendu :
        // Compétence : Java => Développeurs : [Alice, Bob]
        // Compétence : HTML => Développeurs : [Bob]
        // Compétence : SQL => Développeurs : [Alice]
        // Compétence : Python => Développeurs : [Alice, Charlie]

    }

}
