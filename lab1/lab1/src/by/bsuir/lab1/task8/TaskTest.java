package by.bsuir.lab1.task8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TaskTest {
    @Test
    public void test_1() {
        int[] actual = Task.solve(new double[]{0, 1, 2, 3, 4, 6, 7, 9}, new double[]{2, 5, 8, 9, 10});
        int[] expected = new int[]{3, 5, 7, 8, 9};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void test_2() {
        int[] actual = Task.solve(new double[]{0}, new double[]{10});
        int[] expected = new int[]{1};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void test_3() {
        int[] actual = Task.solve(new double[]{0}, new double[]{-10});
        int[] expected = new int[]{0};
        Assert.assertArrayEquals(expected, actual);
    }

}
