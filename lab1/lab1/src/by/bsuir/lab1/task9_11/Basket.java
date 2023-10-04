package by.bsuir.lab1.task9_11;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Basket with balls
 */
public class Basket {

    private final List<Ball> balls;

    /**
     * Creates ball with given list
     *
     * @param balls given list
     */
    public Basket(List<Ball> balls) {
        this.balls = new ArrayList<Ball>(balls);
    }

    /**
     * Creates ball with given array
     *
     * @param balls given array
     */
    public Basket(Ball[] balls) {
        this.balls = new ArrayList<Ball>(List.of(balls));
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void removeBall(int index) {
        balls.remove(index);
    }

    public Ball getBall(int index) {
        return balls.get(index);
    }

    /**
     * Calculates ball with given color
     *
     * @param color given color
     * @return number of balls with given color
     */
    public int countByColor(Color color) {
        int counter = 0;

        for (Ball ball : balls) {
            if (ball.getColor() == color) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Calculates total weight of all balls in basket
     *
     * @return total weight
     */
    public double calculateTotalWeight() {
        double totalWeight = 0;

        for (Ball ball : balls) {
            totalWeight += ball.getWeight();
        }

        return totalWeight;
    }

}
