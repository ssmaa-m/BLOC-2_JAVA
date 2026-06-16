public enum DifficulteTache {
    FACILE("Facile") , MOYENNE("Moyenne") , DIFFICILE ("Difficile"), EXPERTE ("Experte");

    private String nom;

    DifficulteTache(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
