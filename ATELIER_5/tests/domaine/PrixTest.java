package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub    = new Prix(TypePromo.PUB , 10);
        prixSolde  = new Prix(TypePromo.SOLDE, 30);
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub.definirPrix(3,15);
    }

    @Test
    @DisplayName("Test du constructeur avec valeur null")
    void testPrix1 () {
        assertThrows(IllegalArgumentException.class,() -> new Prix(null,5));
    }

    @ParameterizedTest
    @DisplayName("Test du constructeur avec valeurs <= 0")
    @ValueSource(doubles = {-6 , 0 , -25})
    void testPrix2 (double valeur) {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB , valeur));
    }

    @Test
    @DisplayName("Verifier que le type de la promo correspond bien a celle passée en parametre du constructeur")
    void TestgetTypePromo() {
        assertAll(
                () -> assertNull(prixAucune.getTypePromo()),
                () -> assertEquals(TypePromo.PUB, prixPub.getTypePromo()),
                () -> assertEquals(TypePromo.SOLDE, prixSolde.getTypePromo())
        );
    }

    @Test
    @DisplayName("Verification que la valeur de la promo est initialisée avec les bonne valeur")
    void TestgetValeurPromo() {
        assertAll(
                () -> assertEquals(0 , prixAucune.getValeurPromo()),
                () -> assertEquals(10 , prixPub.getValeurPromo()),
                () -> assertEquals(30,prixSolde.getValeurPromo())
        );
    }


    @ParameterizedTest
    @DisplayName("TEST DEFINIR PRIX")
    @ValueSource (ints = {0 , -1})
    void definirPrix(int valeurs) {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class , () -> prixPub.definirPrix(valeurs , 15)),
                () -> assertThrows(IllegalArgumentException.class , () -> prixPub.definirPrix(3, valeurs))
        );
    }

    @Test
    @DisplayName("TEST REMPLACER PRIX")
    void definirPrix2 () {
        prixAucune.definirPrix(10,6);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @ParameterizedTest
    @DisplayName("Verifier parametre negatif ou nul")
    @ValueSource (ints = {0 , -1})
    void getPrixValeurFausse(int valeurs) {
        assertThrows(IllegalArgumentException.class , () -> prixPub.getPrix(valeurs));
    }

    @ParameterizedTest
    @DisplayName("Verifier avec parametre donnée < 9")
    @ValueSource (ints = {1 , 5 , 9})
    void getPrixValeurQuantiteInferieurA10(int valeurs) {
        assertEquals(20 , prixAucune.getPrix(valeurs));
    }

    @ParameterizedTest
    @DisplayName("Verifier avec parametre donnée > 10")
    @ValueSource (ints = {10 , 15 , 20 , 25})
    void getPrixValeurQuantiteSuperieurOuEgalA10 (int valeurs) {
        assertEquals(10 , prixAucune.getPrix(valeurs));
    }

    @Test
    @DisplayName("Verifier avec valeur non autorise pour prix pub et solde")
    void getPrixQuantiteNonAutorisee () {
        assertAll(
                () -> assertThrows(QuantiteNonAutoriseeException.class , () -> prixPub.getPrix(2)),
                () -> assertThrows(QuantiteNonAutoriseeException.class , () -> prixSolde.getPrix(1))
        );
    }
}