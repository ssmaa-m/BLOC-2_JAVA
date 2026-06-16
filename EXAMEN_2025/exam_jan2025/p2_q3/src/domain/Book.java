package domain;

public interface Book {
    String getTitle();

    String getAuthor();

    @Override
    String toString();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
