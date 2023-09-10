package by.bsuir.lab1.task13;

import by.bsuir.lab1.task12.Book;

import java.util.Objects;

/**
 * Extends Book class
 * @see by.bsuir.lab1.task12.Book
 */
public class ProgrammersBook extends Book {
    private final String language;
    private final int level;
    public ProgrammersBook(String title, String author, int price, String language, int level) {
        super(title, author, price);
        this.language = language;
        this.level = level;
    }

    public String getLanguage(){
        return language;
    }

    public int getLevel(){
        return level;
    }

    @Override
    public String toString() {
        return super.toString() + " " + language + " " + level;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProgrammersBook book)) {
            return false;
        }
        if (o.hashCode() != hashCode()) {
            return false;
        }

        return super.equals(o) && this.language.equals(book.language) && this.level == book.level;
    }

    @Override
    public int hashCode() {
        return super.hashCode() * level - language.hashCode();
    }
}
