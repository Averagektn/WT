package by.bsuir.lab1.task14;

import org.junit.Assert;
import org.junit.Test;
public class TaskTest {
    @Test
    public void test_1(){
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        Object book2 = book1.clone();
        Assert.assertEquals(book2, book1);
    }
}
