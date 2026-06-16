package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TrajetTest {

    private Trajet trajet;
    private Caisse caisse;

    @BeforeEach
    void setUp() {
        trajet = new Trajet("10" , LocalDate.now().plusDays(10) , "Bruxelles" , "Wallon");
    }

    @Test
    void peutAjouterTC1() {
        assertThrows(IllegalArgumentException.class , () -> trajet.peutAjouter(null));
    }

    @Test
    void peutAjouterTC2() {
        caisse = new Caisse("CA-0" , LocalDate.now().plusDays(10),"Lyon","Lille",800);
        assertFalse(trajet.peutAjouter(caisse));
    }

    @Test
    void peutAjouterTC3() {
        caisse = new Caisse("CB-0" , LocalDate.now().plusDays(10),"Bruxelles" , "Wallon",800);
        assertTrue(trajet.peutAjouter(caisse));
    }


}