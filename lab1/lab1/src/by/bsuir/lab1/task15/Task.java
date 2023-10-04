package by.bsuir.lab1.task15;

import java.util.ArrayList;

public class Task {

    public static void main(String[] args) {
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488, 12);
        Book book2 = new Book("My", "Cool", 14880, 12);
        Book book3 = new Book("Cool", "Cool", 1485, 2);
        Book book4 = new Book("Book", "NoName", 1487, 5);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.sort(Book::compareTo);
        System.out.println(books);
    }

}
