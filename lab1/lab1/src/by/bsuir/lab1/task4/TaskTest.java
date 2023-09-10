package by.bsuir.lab1.task4;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaskTest {
    @Test
    public void test_1(){
        ArrayList<Integer> actual = Task.solve(new int[]{ 1, 2, 2, 4, 5, 101 });
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(5);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_2(){
        ArrayList<Integer> actual = Task.solve(new int[]{ -1, 2, 0, 4, 19, 10 });
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(4);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_3(){
        ArrayList<Integer> actual = Task.solve(new int[]{ -1, Integer.MAX_VALUE - 1, 0, 4, Integer.MIN_VALUE, 100 });
        ArrayList<Integer> expected = new ArrayList<>();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
