package by.bsuir.lab1.task15;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaskTest {

    @Test
    public void test_1() {
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488, 12);
        Book book2 = new Book("My", "Cool", 14880, 12);
        Book book3 = new Book("Cool", "Cool", 1485, 2);
        Book book4 = new Book("Book", "NoName", 1487, 5);

        ArrayList<Book> actual = new ArrayList<>();
        actual.add(book1);
        actual.add(book2);
        actual.add(book3);
        actual.add(book4);
        actual.sort(Book::compareTo);

        ArrayList<Book> expected = new ArrayList<>();
        expected.add(book3);
        expected.add(book4);
        expected.add(book1);
        expected.add(book2);

        Assert.assertEquals(expected, actual);
    }

}
