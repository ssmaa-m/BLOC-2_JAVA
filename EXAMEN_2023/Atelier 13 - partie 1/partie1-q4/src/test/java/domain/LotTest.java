package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LotTest {

    private Lot lot;

    @BeforeEach
    void setUp() {
        lot = new Lot(2,300,"Caca");
    }

    @Test
    void constructeurTC1(){
        assertThrows(IllegalArgumentException.class , () -> new Lot(0,300, "asma"));
    }

    @Test
    void signalerAffectationTC2() {
        assertTrue(lot.signalerAffectation());
    }

    @Test
    void signalerAffectationTC3() {
        Lot lot1 = new Lot(3, 400, "Poulet");
        lot1.signalerAffectation();
        assertFalse(lot1.signalerAffectation());
    }
}