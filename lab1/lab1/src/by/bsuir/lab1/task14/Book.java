package by.bsuir.lab1.task14;

import java.lang.ref.Cleaner;

public class Book implements Cloneable {
    private final String title;
    private final String author;
    private final int price;
    private static int edition;

    public Book(String title, String author, int price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public int getPrice(){
        return price;
    }

    public int getEdition() {
        return edition;
    }

    @Override
    public String toString(){
        return title + " " + author + " " + price + " " + edition;
    }

    @Override
    public int hashCode() {
        return price * edition * title.hashCode() + author.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) {
            return false;
        }
        if (o.hashCode() != hashCode()) {
            return false;
        }

        return this.title.equals(book.title) && this.author.equals(book.author) && this.price == book.price;
    }

    @Override
    protected Object clone() {
        return new Book(title, author, price);
    }
}
