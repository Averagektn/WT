package by.bsuir.lab1.task13;

import org.junit.Assert;
import org.junit.Test;
public class TaskTest {
    @Test
    public void test_1(){
        ProgrammersBook book1 = new ProgrammersBook("MyCoolBook", "CoolAuthor",
                1488, "C#", 100);
        ProgrammersBook book2 = new ProgrammersBook("AnotherBook", "Noname", 0,
                "Java", 0);
        Assert.assertFalse(book1.equals(book2));
    }

    @Test
    public void test_2(){
        ProgrammersBook book1 = new ProgrammersBook("MyCoolBook", "CoolAuthor",
                1488, "C#", 100);
        ProgrammersBook book2 = new ProgrammersBook("MyCoolBook", "CoolAuthor",
                1488, "C#", 100);
        Assert.assertTrue(book1.equals(book2));
    }

    @Test
    public void test_3() {
        ProgrammersBook book1 = new ProgrammersBook("MyCoolBook", "CoolAuthor",
                1488, "C#", 100);
        String expected = "MyCoolBook CoolAuthor 1488 C# 100";
        String actual = book1.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_4() {
        ProgrammersBook book1 = new ProgrammersBook("MyCoolBook", "CoolAuthor",
                1488, "C#", 100);
        int expected = (book1.getPrice() * book1.getTitle().hashCode() + book1.getAuthor().hashCode())
                * book1.getLevel() - book1.getLanguage().hashCode();
        int actual = book1.hashCode();
        Assert.assertEquals(expected, actual);
    }
}
