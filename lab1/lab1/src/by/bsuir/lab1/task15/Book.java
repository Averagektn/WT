package by.bsuir.lab1.task15;

public class Book implements Comparable<Book> {

    private final String title;

    private final String author;

    private final int price;

    private static int edition;

    private final int isbn;

    public Book(String title, String author, int price, int isbn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    public static void setEdition(int edition) {
        Book.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public int getEdition() {
        return edition;
    }

    @Override
    public String toString() {
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
    public int compareTo(Book o) {
        if (o.isbn == isbn) {
            return 0;
        }
        if (isbn > o.isbn) {
            return 1;
        }
        return -1;
    }

}
