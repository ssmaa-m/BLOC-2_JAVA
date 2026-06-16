package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private Moniteur moniteur;
    private Stage stage;
    private Sport sportCompetent;

    private void preparerMoniteurAvecNStages(int nombreDeStages) {
        for (int numSemaine = 1; numSemaine <= nombreDeStages; numSemaine++) {
            Stage stageAjouter = Mockito.mock(Stage.class);
            Mockito.when(stageAjouter.getSport()).thenReturn(sportCompetent);
            Mockito.when(stageAjouter.getMoniteur()).thenReturn(null);
            Mockito.when(stageAjouter.getNumeroDeSemaine()).thenReturn(numSemaine);
            moniteur.ajouterStage(stageAjouter);
        }
    }

    @BeforeEach
    void setUp() {
        stage = Mockito.mock(Stage.class);
        sportCompetent = Mockito.mock(Sport.class);
        moniteur = new MoniteurImpl("asma");

        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);
        Mockito.when(stage.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(stage.getMoniteur()).thenReturn(null);
        Mockito.when(stage.getSport()).thenReturn(sportCompetent);
    }

    @Test
    void TC1 () {
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> assertEquals(1 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC2 () {
        preparerMoniteurAvecNStages(1);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> assertEquals(2 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC3 () {
        preparerMoniteurAvecNStages(2);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> assertEquals(3,moniteur.nombreDeStages()),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC4 () {
        preparerMoniteurAvecNStages(3);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC5 () {
        preparerMoniteurAvecNStages(4);
        moniteur.ajouterStage(stage);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stage)),
                () -> assertEquals(5 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage , Mockito.times(1)).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC6 () {
        preparerMoniteurAvecNStages(4);
        Stage stage1 = Mockito.mock(Stage.class);
        Mockito.when(stage1.getSport()).thenReturn(sportCompetent);
        Mockito.when(stage1.getMoniteur()).thenReturn(null);
        Mockito.when(stage1.getNumeroDeSemaine()).thenReturn(1);

        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stage1)),
                () -> assertFalse(moniteur.contientStage(stage1)),
                () -> assertEquals(4 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage1 , Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC7 () {
        preparerMoniteurAvecNStages(4);
        Stage stage1 = Mockito.mock(Stage.class);
        Moniteur moniteur1 = new MoniteurImpl("Hajar");
        Mockito.when(stage1.getMoniteur()).thenReturn(moniteur1);
        Mockito.when(stage1.getSport()).thenReturn(sportCompetent);
        Mockito.when(stage1.getNumeroDeSemaine()).thenReturn(8);

        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stage1)),
                () -> assertFalse(moniteur.contientStage(stage1)),
                () -> assertEquals(4 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage1 , Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC8 () {
        preparerMoniteurAvecNStages(4);
        Stage stage1 = Mockito.mock(Stage.class);
        Mockito.when(stage1.getMoniteur()).thenReturn(moniteur);
        Mockito.when(stage1.getSport()).thenReturn(sportCompetent);
        Mockito.when(stage1.getNumeroDeSemaine()).thenReturn(8);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage1)),
                () -> assertTrue(moniteur.contientStage(stage1)),
                () -> assertEquals(5 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage1 , Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void TC9 () {
        preparerMoniteurAvecNStages(4);
        Stage stage1 = Mockito.mock(Stage.class);
        Sport sportNonCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportNonCompetent.contientMoniteur(moniteur)).thenReturn(false);
        Mockito.when(stage1.getMoniteur()).thenReturn(null);
        Mockito.when(stage1.getSport()).thenReturn(sportNonCompetent);
        Mockito.when(stage1.getNumeroDeSemaine()).thenReturn(8);

        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stage1)),
                () -> assertFalse(moniteur.contientStage(stage1)),
                () -> assertEquals(4 , moniteur.nombreDeStages()),
                () -> Mockito.verify(stage1 , Mockito.never()).enregistrerMoniteur(moniteur)

        );
    }


}