package by.bsuir.lab1.task6;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void test_1() {
        int[][] actual = Task.solve(new int[]{});
        int[][] expected = new int[][]{};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void test_2() {
        int[][] actual = Task.solve(new int[]{1, 2, 3, 4, 5});
        int[][] expected = new int[][]{
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 1},
                {3, 4, 5, 1, 2},
                {4, 5, 1, 2, 3},
                {5, 1, 2, 3, 4}
        };
        Assert.assertArrayEquals(expected, actual);
    }

}
