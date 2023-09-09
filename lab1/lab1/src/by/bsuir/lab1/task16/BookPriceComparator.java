package by.bsuir.lab1.task16;

import java.util.Comparator;

public class BookPriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getPrice() == o2.getPrice()){
            return 0;
        }
        if (o1.getPrice() > o2.getPrice()){
            return 1;
        }
        return -1;
    }
}
