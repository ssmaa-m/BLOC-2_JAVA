package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PoulaillerTest {

    private Poulailler poulailler;
    private Lot lot;

    @BeforeEach
    void setUp() {
        poulailler = new Poulailler("52GX", 500);
        lot = Mockito.mock(Lot.class);
        Mockito.when(lot.signalerAffectation()).thenReturn(false);
    }


    @Test
    void ajouterLot() {
        assertAll(
                () -> assertFalse(poulailler.ajouterLot(lot)),
                () -> assertFalse(poulailler.getTousLesLots().contains(lot))
        );
    }
}