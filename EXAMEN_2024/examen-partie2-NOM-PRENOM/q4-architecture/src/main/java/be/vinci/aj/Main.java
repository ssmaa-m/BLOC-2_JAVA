package be.vinci.aj;

import be.vinci.aj.domain.DomaineFactory;
import be.vinci.aj.services.GestionTrains;

public class Main {

    public static void main(String[] args) {
        DomaineFactory factory = new DomaineFactory();
        GestionTrains gestionTrains = new GestionTrains(factory);
        gestionTrains.demarrerTrains();
    }

}