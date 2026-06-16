public enum TypeTache {
    DEVELOPPEMENT("development") , TEST("test") , DOCUMENTATION("documentation");

    private String nom;

    TypeTache(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
