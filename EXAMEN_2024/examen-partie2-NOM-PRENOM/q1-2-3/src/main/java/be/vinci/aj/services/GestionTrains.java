package be.vinci.aj.services;

import be.vinci.aj.domain.TrainImpl;

import java.util.ArrayList;
import java.util.List;

public class GestionTrains {

    public void demarrerTrains() {

        List<TrainImpl> trains = List.of(
                new TrainImpl("Bruxelles", "Namur", 1500),
                new TrainImpl("Bruxelles", "Ostende", 2000),
                new TrainImpl("Bruxelles", "Louvain", 1000)
        );

        for (TrainImpl train : trains) {
            Thread thread  = new Thread(train::demarrer);
            thread.start();
        }


    }

}
