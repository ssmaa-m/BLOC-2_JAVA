package compteur_thread;

import java.time.LocalDateTime;

public class TestCompteurThread {

    public static void main(String[] args) throws InterruptedException {
        CompteurThread[] compteurs = {
                new CompteurThread("Bolt", 10),
                new CompteurThread("Jakson", 10),
                new CompteurThread("Robert", 10),
                new CompteurThread("Stéphanie", 10)};
        LocalDateTime start = LocalDateTime.now();



        for (int i = 0; i < compteurs.length; i++) {
            compteurs[i].start();
        }


        for (int i = 0; i < compteurs.length; i++) {
             compteurs[i].join();
        }

        LocalDateTime end = LocalDateTime.now();
        long duration = java.time.Duration.between(start, end).toMillis();
        System.out.println("Tout le monde a fini de compter !");

    }

}
