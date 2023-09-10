package by.bsuir.lab1.task5;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void test_1(){
        int actual = Task.solve(new int[]{1, 2, 5, 3, 4});
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_2(){
        int actual = Task.solve(new int[]{2, 4, 4, 4, 3, 5, 6}, true);
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_3(){
        int actual = Task.solve(new int[]{2, 4, 4, 4, 3, 5, 6}, false);
        int expected = 3;
        Assert.assertEquals(expected, actual);
    }
}