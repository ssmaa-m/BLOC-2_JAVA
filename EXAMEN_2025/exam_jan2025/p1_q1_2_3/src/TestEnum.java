import java.util.Arrays;

public class TestEnum {

    public static void main(String[] args) {
        System.out.println("=== Test : Enumérations ===");
        boolean isDifficulteTacheValid = validateDifficulteTache();
        boolean isTypeTacheValid = validateTypeTache();

        if (isDifficulteTacheValid && isTypeTacheValid) {
            System.out.println("Toutes les énumérations sont correctes !");
        } else {
            System.out.println("Il y a des erreurs dans votre implémentation des énumérations.");
        }
    }

    private static boolean validateDifficulteTache() {
        System.out.println("Validation de l'énumération DifficulteTache...");
        String[] expectedNames = {"FACILE", "MOYENNE", "DIFFICILE", "EXPERTE"};
        String[] expectedNoms = {"Facile", "Moyenne", "Difficile", "Experte"};

        try {
            // Vérifier les noms des constantes
            String[] actualNames = Arrays.stream(DifficulteTache.values())
                    .map(Enum::name)
                    .toArray(String[]::new);
            if (!Arrays.equals(expectedNames, actualNames)) {
                System.out.println("Les noms des constantes de DifficulteTache sont incorrects.");
                return false;
            }

            // Vérifier les noms via le getter
            for (int i = 0; i < expectedNoms.length; i++) {
                if (!DifficulteTache.values()[i].getNom().equals(expectedNoms[i])) {
                    System.out.printf("Le nom associé à %s est incorrect. Attendu: '%s', Obtenu: '%s'%n",
                            expectedNames[i], expectedNoms[i], DifficulteTache.values()[i].getNom());
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la validation de DifficulteTache : " + e.getMessage());
            return false;
        }

        System.out.println("L'énumération DifficulteTache est correcte.");
        return true;
    }

    private static boolean validateTypeTache() {
        System.out.println("Validation de l'énumération TypeTache...");
        String[] expectedNames = {"DEVELOPPEMENT", "TEST", "DOCUMENTATION"};
        String[] expectedNoms = {"development", "test", "documentation"};

        try {
            // Vérifier les noms des constantes
            String[] actualNames = Arrays.stream(TypeTache.values())
                    .map(Enum::name)
                    .toArray(String[]::new);
            if (!Arrays.equals(expectedNames, actualNames)) {
                System.out.println("Les noms des constantes de TypeTache sont incorrects.");
                return false;
            }

            // Vérifier les noms via le getter
            for (int i = 0; i < expectedNoms.length; i++) {
                if (!TypeTache.values()[i].getNom().equals(expectedNoms[i])) {
                    System.out.printf("Le nom associé à %s est incorrect. Attendu: '%s', Obtenu: '%s'%n",
                            expectedNames[i], expectedNoms[i], TypeTache.values()[i].getNom());
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la validation de TypeTache : " + e.getMessage());
            return false;
        }

        System.out.println("L'énumération TypeTache est correcte.");
        return true;
    }
}