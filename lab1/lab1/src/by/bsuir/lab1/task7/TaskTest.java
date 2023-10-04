package by.bsuir.lab1.task7;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void test_1() {
        double[] actual = new double[]{1, 5, 2, 8, 0, -2};
        Task.sort(actual);
        double[] expected = new double[]{-2, 0, 1, 2, 5, 8};
        Assert.assertArrayEquals(expected, actual, 0.0);
    }

    @Test
    public void test_2() {
        double[] actual = new double[]{1, 2, 3, 4, 5, 6};
        Task.sort(actual);
        double[] expected = new double[]{1, 2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected, actual, 0.0);
    }

    @Test
    public void test_3() {
        double[] actual = new double[]{6, 5, 4, 3, 2, 1};
        Task.sort(actual);
        double[] expected = new double[]{1, 2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected, actual, 0.0);
    }

    @Test
    public void test_4() {
        double[] actual = new double[]{};
        Task.sort(actual);
        double[] expected = new double[]{};
        Assert.assertArrayEquals(expected, actual, 0.0);
    }

}
