package by.bsuir.lab1.task3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaskTest {
    @Test
    public void test_1(){
        ArrayList<Double> actual = Task.solve(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.1);
        ArrayList<Double> expected = new ArrayList<>();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_2(){
        ArrayList<Double> actual = Task.solve(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.1);
        ArrayList<Double> expected = new ArrayList<>();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_3(){
        ArrayList<Double> actual = Task.solve(0, Double.POSITIVE_INFINITY, 0.1);
        ArrayList<Double> expected = new ArrayList<>();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_4(){
        ArrayList<Double> actual = Task.solve(-5, 0, Double.POSITIVE_INFINITY);
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(3.38);
        actual.replaceAll(aDouble -> Math.floor(aDouble * 100) / 100);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_5(){
        ArrayList<Double> actual = Task.solve(-5, 0, -1);
        ArrayList<Double> expected = new ArrayList<>();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void test_6(){
        ArrayList<Double> actual = Task.solve(1, 2, 0.1);
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(1.55);
        expected.add(1.96);
        expected.add(2.57);
        expected.add(3.60);
        expected.add(5.79);
        expected.add(14.10);
        expected.add(-34.24);
        expected.add(-7.70);
        expected.add(-4.29);
        expected.add(-2.93);
        expected.add(-2.19);
        actual.replaceAll(aDouble -> Math.floor(aDouble * 100) / 100);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

}
