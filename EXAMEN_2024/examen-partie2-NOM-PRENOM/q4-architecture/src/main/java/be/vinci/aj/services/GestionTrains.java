package be.vinci.aj.services;


import be.vinci.aj.domain.DomaineFactory;
import be.vinci.aj.domain.TrainI;

public class GestionTrains {

    private final DomaineFactory factory;

    public GestionTrains(DomaineFactory factory) {
        this.factory = factory;
    }


    public void demarrerTrains() {
        TrainI train1 = factory.createTrain("Bruxelles", "Namur", 1500);
        TrainI train2 = factory.createTrain("Bruxelles", "Ostende", 2000);
        TrainI train3 = factory.createTrain("Bruxelles", "Louvain", 1000);

        train1.demarrer();
        train2.demarrer();
        train3.demarrer();
    }

}
