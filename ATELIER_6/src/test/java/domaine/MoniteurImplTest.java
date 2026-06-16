package domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private Moniteur moniteur;
    private Sport sportCompetent;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("asma");
        sportCompetent = new SportStub(true);
    }

    private void preparerMoniteurAvecNStages(int nombreDeStages) {
        for (int numSemaine = 1; numSemaine <= nombreDeStages; numSemaine++) {
            moniteur.ajouterStage(new StageStub(sportCompetent, null,  numSemaine));
        }
    }

    // METHODE AJOUTER STAGE
    @Test
    void TC1 () {
        StageStub stage = new StageStub(sportCompetent , null , 1);
        assertTrue(moniteur.ajouterStage(stage));
        assertTrue(moniteur.contientStage(stage));
        assertEquals(1 , moniteur.nombreDeStages());
    }

    @Test
    void TC2 () {
        preparerMoniteurAvecNStages(1);
        StageStub stage = new StageStub(sportCompetent , null , 2);
        assertTrue(moniteur.ajouterStage(stage));
        assertTrue(moniteur.contientStage(stage));
        assertEquals(2 , moniteur.nombreDeStages());
    }

    @Test
    void TC3 () {
        preparerMoniteurAvecNStages(2);
        StageStub stage = new StageStub(sportCompetent , null, 3);
        assertTrue(moniteur.ajouterStage(stage));
        assertTrue(moniteur.contientStage(stage));
        assertEquals(3 , moniteur.nombreDeStages());
    }

    @Test
    void TC4 () {
        preparerMoniteurAvecNStages(3);
        StageStub stage = new StageStub(sportCompetent , null , 4);
        assertTrue(moniteur.ajouterStage(stage));
        assertTrue(moniteur.contientStage(stage));
        assertEquals(4 , moniteur.nombreDeStages());
    }

    @Test
    void TC5 () {
        StageStub stage = new StageStub(sportCompetent , null,4);
        preparerMoniteurAvecNStages(3);
        moniteur.ajouterStage(stage);
        assertTrue(moniteur.supprimerStage(stage));
        assertFalse(moniteur.contientStage(stage));
        assertEquals(3 , moniteur.nombreDeStages());
    }

    @Test
    void TC6 () {
        StageStub stage = new StageStub(sportCompetent , null , 3);
        preparerMoniteurAvecNStages(2);
        moniteur.ajouterStage(stage);
        assertTrue(moniteur.supprimerStage(stage));
        assertFalse(moniteur.contientStage(stage));
        assertEquals(2 , moniteur.nombreDeStages());
    }

    @Test
    void TC7 () {
        StageStub stage = new StageStub(sportCompetent , null, 2);
        preparerMoniteurAvecNStages(1);
        moniteur.ajouterStage(stage);
        assertTrue(moniteur.supprimerStage(stage));
        assertFalse(moniteur.contientStage(stage));
        assertEquals(1 , moniteur.nombreDeStages());
    }

    @Test
    void TC8 () {
        StageStub stage = new StageStub(sportCompetent , null , 1);
        moniteur.ajouterStage(stage);
        assertTrue(moniteur.supprimerStage(stage));
        assertFalse(moniteur.contientStage(stage));
        assertEquals(0 , moniteur.nombreDeStages());
    }

    @Test
    void TC9 () {
        preparerMoniteurAvecNStages(3);
        StageStub stage = new StageStub(sportCompetent , null , 4);
        moniteur.ajouterStage(stage);
        assertFalse(moniteur.ajouterStage(stage));
        assertEquals(4 , moniteur.nombreDeStages());
    }

    @Test
    void TC10 () {
        preparerMoniteurAvecNStages(4);
        StageStub stage = new StageStub(sportCompetent , null , 1);
        assertFalse(moniteur.ajouterStage(stage));
        assertEquals(4 , moniteur.nombreDeStages());
    }

    @Test
    void TC11 () {
        preparerMoniteurAvecNStages(4);
        StageStub stage = new StageStub(sportCompetent , null, 5);
        assertFalse(moniteur.supprimerStage(stage));
        assertEquals(4 , moniteur.nombreDeStages());
    }

    @Test
    void TC12 () {
        preparerMoniteurAvecNStages(4);
        StageStub stage = new StageStub(sportCompetent , moniteur , 4);
        assertFalse(moniteur.ajouterStage(stage));
    }

    @Test
    void TC13 () {
        preparerMoniteurAvecNStages(4);
        SportStub sportStub = new SportStub(false);

        StageStub stageStub = new StageStub(sportStub,null ,5);
        assertFalse(moniteur.ajouterStage(stageStub));
        assertFalse(moniteur.contientStage(stageStub));
        assertEquals(4 , moniteur.nombreDeStages());

    }


}