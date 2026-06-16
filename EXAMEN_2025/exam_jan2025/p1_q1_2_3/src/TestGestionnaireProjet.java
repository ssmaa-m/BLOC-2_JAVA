import java.util.Arrays;
import java.util.Map;

public class TestGestionnaireProjet {
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

        // Test : Filtrer les tâches par état
        System.out.println("\n=== Test : Filtrer les tâches par état ===");
        System.out.println("Tâches en cours (attendu : 1 tâche)");
        GestionnaireProjet.filtrerTachesParEtat(projet, EtatTache.EN_COURS).forEach(t -> System.out.println("- " + t));
        // Résultat attendu :
        // - Implémenter l'interface

        System.out.println("\nTâches terminées (attendu : 1 tâche)");
        GestionnaireProjet.filtrerTachesParEtat(projet, EtatTache.TERMINEE).forEach(t -> System.out.println("- " + t));
        // Résultat attendu :
        // - Optimiser les requêtes SQL

        System.out.println("\nTâches bloquées (attendu : 1 tâche)");
        GestionnaireProjet.filtrerTachesParEtat(projet, EtatTache.BLOQUEE).forEach(t -> System.out.println("- " + t));
        // Résultat attendu :
        // - Développer la page d'accueil

        // Test : Calcul de la charge de travail par priorité
        System.out.println("\n=== Test : Calcul de la charge de travail par priorité ===");
        System.out.println("Charge de travail pour priorité HAUTE (attendu : 18 heures)");
        System.out.println(GestionnaireProjet.calculerChargeTravailParPriorite(projet, Priorite.HAUTE));
        // Résultat attendu : 18

        System.out.println("\nCharge de travail pour priorité MOYENNE (attendu : 15 heures)");
        System.out.println(GestionnaireProjet.calculerChargeTravailParPriorite(projet, Priorite.MOYENNE));
        // Résultat attendu : 15

        System.out.println("\nCharge de travail pour priorité BASSE (attendu : 5 heures)");
        System.out.println(GestionnaireProjet.calculerChargeTravailParPriorite(projet, Priorite.BASSE));
        // Résultat attendu : 5

        // Test : Liste des développeurs impliqués
        System.out.println("\n=== Test : Développeurs impliqués ===");
        System.out.println("Développeurs impliqués (attendu : 3 développeurs)");
        GestionnaireProjet.obtenirDeveloppeursImpliques(projet).forEach(dev -> System.out.println("- " + dev));
        // Résultat attendu :
        // - Alice
        // - Bob
        // - Charlie

        // Test : Calcul total des heures estimées
        System.out.println("\n=== Test : Calcul du total des heures estimées ===");
        System.out.println("Total des heures estimées (attendu : 38 heures)");
        int totalHeures = GestionnaireProjet.calculerTotalHeuresEstimees(projet);
        System.out.println("Total : " + totalHeures);
        // Résultat attendu : 38

        // Test : Regrouper la charge de travail par état
        System.out.println("\n=== Test : Regrouper la charge de travail par état ===");
        System.out.println("Charge totale par état (attendu : A_FAIRE=10, EN_COURS=15, TERMINEE=8, BLOQUEE=5)");
        Map<EtatTache, Integer> chargeParEtat = GestionnaireProjet.regrouperChargeParEtat(projet);
        chargeParEtat.forEach((etat, charge) -> System.out.println(etat + " => " + charge + " heures"));
        // Résultat attendu :
        // A_FAIRE => 10 heures
        // EN_COURS => 15 heures
        // TERMINEE => 8 heures
        // BLOQUEE => 5 heures
    }
}
