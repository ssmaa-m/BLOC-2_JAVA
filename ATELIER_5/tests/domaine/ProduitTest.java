package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Date;


class ProduitTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;
    private Produit produit1;
    private Produit produit2;
    private LocalDate DATE_ANNEE = LocalDate.now().minusYears(1);
    private LocalDate DATE_MOIS  = LocalDate.now().minusMonths(1);
    private LocalDate DATE_MAINTENANT = LocalDate.now();


    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub    = new Prix(TypePromo.PUB , 10);
        prixSolde  = new Prix(TypePromo.SOLDE, 30);
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub.definirPrix(3,15);

        produit1 = new Produit("nom1","marque1","rayon1");
        produit2 = new Produit("nom2","marque2","rayon2");

        produit1.ajouterPrix(DATE_ANNEE, prixAucune);
        produit1.ajouterPrix(DATE_MOIS, prixPub);
        produit1.ajouterPrix(DATE_MAINTENANT, prixSolde);
    }

    @Test
    @DisplayName("Exeption en cas de parametre invalide pour le constructeur ")
    void testConstructeur () {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class , () -> new Produit(null , "marque1" , "rayon1")),
                () -> assertThrows(IllegalArgumentException.class , () -> new Produit("nom1" , null, "rayon1")),
                () -> assertThrows(IllegalArgumentException.class , () -> new Produit("nom1" , "marque1", null)),
                () -> assertThrows(IllegalArgumentException.class , () -> new Produit(" " , "marque1" , "rayon1")),
                () -> assertThrows(IllegalArgumentException.class , () -> new Produit("nom1" , " " , "rayon1")),
                () -> assertThrows(IllegalArgumentException.class , () -> new Produit("nom1" , "marque1" , " "))
        );

    }

    @Test
    void getMarque() {
        assertEquals("marque2" , produit2.getMarque());
    }

    @Test
    void getNom() {
        assertEquals("nom2" , produit2.getNom());
    }

    @Test
    void getRayon() {
        assertEquals("rayon2", produit2.getRayon());
    }

    @Test
    void ajouterPrixAvecParametreNull() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class , () -> produit2.ajouterPrix(null , prixPub)),
                () -> assertThrows(IllegalArgumentException.class , () -> produit2.ajouterPrix(DATE_MAINTENANT, null))
        );
    }

    @Test
    void ajouterPrixAvecDateDejaPresent () {
        assertThrows(DateDejaPresenteException.class , () -> produit1.ajouterPrix(DATE_ANNEE , new Prix()));
    }

    @Test
    void ajouterPrix () {
        LocalDate date_days = LocalDate.now().minusDays(5);
        produit1.ajouterPrix(date_days , prixPub);
        assertEquals(prixPub , produit1.getPrix(date_days));
    }

    @Test
    void getPrixNonDisponible() {
        assertThrows(PrixNonDisponibleException.class , () -> produit1.getPrix(LocalDate.now().minusYears(2)));
    }

    @Test
    void getProduitSansPrix () {
        assertThrows(PrixNonDisponibleException.class , () -> produit2.getPrix(DATE_ANNEE));
    }

    @Test
    void getPrixEntreDeuxDate() {
        LocalDate entre_date = LocalDate.now().minusMonths(5);
        assertEquals(prixAucune , produit1.getPrix(entre_date));
    }

    @Test
    void testEquals() {
        Produit produit3 = new Produit("nom2","marque2","rayon2");
        assertEquals(produit3 , produit2);
    }

    @Test
    void testEqualsPasLeMemeNoms () {
        Produit produit3 = new Produit("nom3","marque2","rayon2");
        assertNotEquals(produit3 , produit2);
    }

    @Test
    void testEqualsPasLaMemeMarque () {
        Produit produit3 = new Produit("nom2","marque3","rayon2");
        assertNotEquals(produit3 , produit2);
    }

    @Test
    void testEqualsPasLeMemeRayon () {
        Produit produit3 = new Produit("nom2","marque2","rayon3");
        assertNotEquals(produit3 , produit2);
    }

    @Test
    void testHashCode() {
        Produit produit = new Produit("nom2", "marque2","rayon2");
        assertEquals(produit.hashCode() , produit2.hashCode());
    }
}