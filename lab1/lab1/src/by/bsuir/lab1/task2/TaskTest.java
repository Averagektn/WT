package by.bsuir.lab1.task2;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void test_1(){
        boolean actual = Task.solve(1, 0);
        Assert.assertTrue(actual);
    }

    @Test
    public void test_2(){
        boolean actual = Task.solve(4, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void test_3(){
        boolean actual = Task.solve(4.001, 2);
        Assert.assertFalse(actual);
    }

    @Test
    public void test_4(){
        boolean actual = Task.solve(6, -1.1);
        Assert.assertTrue(actual);
    }

    @Test
    public void test_5(){
        boolean actual = Task.solve(-1, -0.512);
        Assert.assertTrue(actual);
    }

    @Test
    public void test_6(){
        boolean actual = Task.solve(1, 5);
        Assert.assertTrue(actual);
    }

}
