package by.bsuir.lab1.task12;

import org.junit.Assert;
import org.junit.Test;
public class TaskTest {
    @Test
    public void test_1(){
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        Book book2 = new Book("AnotherBook", "Noname", 0);
        Assert.assertFalse(book1.equals(book2));
    }

    @Test
    public void test_2(){
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        Book book2 = new Book("MyCoolBook", "CoolAuthor", 1488);
        Assert.assertTrue(book1.equals(book2));
    }

    @Test
    public void test_3() {
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        String expected = "MyCoolBook CoolAuthor 1488";
        String actual = book1.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_4() {
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        int expected = book1.getPrice() * book1.getTitle().hashCode() + book1.getAuthor().hashCode();
        int actual = book1.hashCode();
        Assert.assertEquals(expected, actual);
    }
}
