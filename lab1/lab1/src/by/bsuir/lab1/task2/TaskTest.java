package by.bsuir.lab1.task2;

import org.junit.Assert;
import org.junit.Test;
public class TaskTest {
    @Test
    public void test_1(){
        boolean actual = Task.solve(1, 0);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test_2(){
        boolean actual = Task.solve(4, 2);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test_3(){
        boolean actual = Task.solve(4.001, 2);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test_4(){
        boolean actual = Task.solve(6, -1.1);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test_5(){
        boolean actual = Task.solve(-1, -0.512);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test_6(){
        boolean actual = Task.solve(1, 5);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
}