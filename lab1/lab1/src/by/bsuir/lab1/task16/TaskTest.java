package by.bsuir.lab1.task16;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaskTest {
    @Test
    public void test_1(){
        ArrayList<Book> actual = new ArrayList<>();

        actual.add(new Book("title4", "author3", 14));
        actual.add(new Book("title3", "author6", 9));
        actual.add(new Book("title2", "author0", 4));
        actual.add(new Book("title1", "author1", 1));
        actual.add(new Book("title0", "author312", 4));

        actual.sort(new BookTitleComparator());

        ArrayList<Book> expected = new ArrayList<>();
        expected.add(new Book("title0", "author312", 4));
        expected.add(new Book("title1", "author1", 1));
        expected.add(new Book("title2", "author0", 4));
        expected.add(new Book("title3", "author6", 9));
        expected.add(new Book("title4", "author3", 14));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_2(){
        ArrayList<Book> actual = new ArrayList<>();

        actual.add(new Book("title4", "author3", 14));
        actual.add(new Book("title3", "author6", 9));
        actual.add(new Book("title2", "author0", 4));
        actual.add(new Book("title1", "author1", 1));
        actual.add(new Book("title0", "author312", 4));

        actual.sort(new BookTitleComparator());
        actual.sort(new BookAuthorComparator());

        ArrayList<Book> expected = new ArrayList<>();
        expected.add(new Book("title2", "author0", 4));
        expected.add(new Book("title1", "author1", 1));
        expected.add(new Book("title4", "author3", 14));
        expected.add(new Book("title0", "author312", 4));
        expected.add(new Book("title3", "author6", 9));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_3(){
        ArrayList<Book> actual = new ArrayList<>();

        actual.add(new Book("title4", "author3", 14));
        actual.add(new Book("title3", "author6", 9));
        actual.add(new Book("title2", "author0", 4));
        actual.add(new Book("title1", "author1", 1));
        actual.add(new Book("title0", "author312", 4));

        actual.sort(new BookTitleComparator());
        actual.sort(new BookAuthorComparator());
        actual.sort(new BookPriceComparator());

        ArrayList<Book> expected = new ArrayList<>();
        expected.add(new Book("title1", "author1", 1));
        expected.add(new Book("title2", "author0", 4));
        expected.add(new Book("title0", "author312", 4));
        expected.add(new Book("title3", "author6", 9));
        expected.add(new Book("title4", "author3", 14));

        Assert.assertEquals(expected, actual);
    }

}
