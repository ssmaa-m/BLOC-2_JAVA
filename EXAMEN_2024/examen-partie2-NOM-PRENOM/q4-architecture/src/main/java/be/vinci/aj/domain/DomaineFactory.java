package be.vinci.aj.domain;

public class DomaineFactory {

    public TrainI createTrain(String depart , String arrive , int tempParcours){
        return new TrainImpl(depart , arrive , tempParcours);
    }

    public Locomotive createLocomotive(String id, String modele, int poids){
        return new LocomotiveImpl(id , modele , poids);
    }

    public Wagon createVehicule(String id, String modele, int poids, int capacite){
        return new WagonImpl(id , modele , poids , capacite);
    }
}
