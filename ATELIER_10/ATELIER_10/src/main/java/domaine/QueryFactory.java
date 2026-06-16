package domaine;

public interface QueryFactory {
    static Query getQuery() {
        return new QueryImpl();
    }
}
