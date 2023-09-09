package by.bsuir.lab1.task16;

import java.util.ArrayList;

public class Task {
    public static void main(String[] args){
        ArrayList<Book> books = new ArrayList<>();

        books.add(new
                Book("title4", "author3", 14));
        books.add(new
                Book("title3", "author6", 9));
        books.add(new
                Book("title2", "author0", 4));
        books.add(new
                Book("title1", "author1", 1));
        books.add(new
                Book("title0", "author312", 4));

        books.sort(new BookAuthorComparator());
        System.out.println(books);
        books.sort(new BookTitleComparator());
        System.out.println(books);
        books.sort(new BookPriceComparator());
        System.out.println(books);
    }
}
