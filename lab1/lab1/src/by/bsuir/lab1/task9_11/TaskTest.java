package by.bsuir.lab1.task9_11;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class TaskTest {
    @Test
    public void test_1() {
        Ball[] testBalls = new Ball[]{
                new Ball(Color.RED, 1.0),
                new Ball(Color.GREEN, 2.6),
                new Ball(Color.BLUE, 5.9)
        };
        Basket basket = new Basket(testBalls);
        basket.addBall(new Ball(Color.BLUE, 4.1));

        int actual = basket.countByColor(Color.BLUE);
        int expected = 2;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_2() {
        Ball[] testBalls = new Ball[]{
                new Ball(Color.RED, 1.0),
                new Ball(Color.GREEN, 2.6),
                new Ball(Color.BLUE, 5.9)
        };
        Basket basket = new Basket(testBalls);
        basket.addBall(new Ball(Color.BLUE, 4.1));

        double actual = basket.calculateTotalWeight();
        double expected = 13.6;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_3() {
        Ball[] testBalls = new Ball[]{
                new Ball(Color.RED, 1.0),
                new Ball(Color.GREEN, 2.6),
                new Ball(Color.BLUE, 5.9)
        };
        Basket basket = new Basket(testBalls);
        basket.addBall(new Ball(Color.GREEN, 4.1));

        int actual = basket.countByColor(Color.GREEN);
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

}
