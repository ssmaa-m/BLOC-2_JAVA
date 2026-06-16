package be.vinci.aj.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TrainImplTest {

    private Locomotive locomotive;
    private Train train;
    private Wagon wagon;

    @BeforeEach
    void setUp() {
        train = new TrainImpl("Bruxelles" , "Liege", 1000);
    }

    @Test
    void ajouterVehiculeTC1() {
        locomotive = Mockito.mock(Locomotive.class);
        Mockito.when(locomotive.getPuissance()).thenReturn(6000);
        Mockito.when(locomotive.getPoids()).thenReturn(2000);

        boolean resultat = train.ajouterVehicule(locomotive);

        assertAll(
                () -> assertTrue(resultat),
                () -> assertTrue(train.getVehicules().contains(locomotive)),
                () -> assertEquals(locomotive , train.getVehicules().get(0)),
                () -> assertEquals(1, train.getVehicules().size())
        );
    }

    @Test
    void ajouterVehiculeTC2() {

        locomotive = Mockito.mock(Locomotive.class);
        Mockito.when(locomotive.getPuissance()).thenReturn(6000);
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        train.ajouterVehicule(locomotive);

        wagon = Mockito.mock(Wagon.class);
        Mockito.when(wagon.getPoids()).thenReturn(2000);
        boolean resultat = train.ajouterVehicule(wagon);

        assertAll(
                () -> assertTrue(resultat),
                () -> assertEquals(2 , train.getVehicules().size()),
                () -> assertEquals(wagon , train.getVehicules().get(1)),
                () -> assertTrue(train.getVehicules().contains(wagon))
        );
    }

    @Test
    void ajouterVehiculeTC3() {
        locomotive = Mockito.mock(Locomotive.class);
        Mockito.when(locomotive.getPuissance()).thenReturn(6000);
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        train.ajouterVehicule(locomotive);

        wagon = Mockito.mock(Wagon.class);
        Mockito.when(wagon.getPoids()).thenReturn(2000);
        train.ajouterVehicule(wagon);

        Wagon wagon2 = Mockito.mock(Wagon.class);
        Mockito.when(wagon2.getPoids()).thenReturn(3000);
        boolean resultat = train.ajouterVehicule(wagon2);

        assertAll(
                () -> assertFalse(resultat),
                () -> assertFalse(train.getVehicules().contains(wagon2)),
                () -> assertEquals(2 , train.getVehicules().size())
        );
    }

    @Test
    void ajouterVehiculeTC4() {
        locomotive = Mockito.mock(Locomotive.class);
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        Mockito.when(locomotive.getPuissance()).thenReturn(1000);

        assertAll(
                () -> assertThrows(IllegalArgumentException.class , () -> train.ajouterVehicule(locomotive)),
                () -> assertEquals(0 , train.getVehicules().size())
        );
    }
}