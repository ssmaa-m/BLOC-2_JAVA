package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

class CamionTest {

    private Camion camion;
    private Trajet trajet;

    @BeforeEach
    void setUp() {
        camion = new Camion("Q-ABC-123", 10,16400);
        trajet = Mockito.mock(Trajet.class);
        Mockito.when(trajet.getDate()).thenReturn(LocalDate.now().plusDays(10));
        Mockito.when(trajet.getVilleDepart()).thenReturn("Marseille");
        Mockito.when(trajet.getVilleArrivee()).thenReturn("Toulouse");
        Mockito.when(trajet.nbCaisses()).thenReturn(15);
    }

    @Test
    void ajouterTrajet() {
         assertFalse(camion.ajouterTrajet(trajet));
    }
}