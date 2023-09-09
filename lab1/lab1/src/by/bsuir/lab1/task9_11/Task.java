package by.bsuir.lab1.task9_11;

import java.awt.Color;

public class Task {
    public static void main(String[] args){
        Ball[] myBalls = new Ball[] {
                new Ball(Color.RED, 1.0),
                new Ball(Color.GREEN, 2.6),
                new Ball(Color.BLUE, 5.9)
        };
        Basket basket = new Basket(myBalls);
        basket.addBall(new Ball(Color.BLUE, 4.1));

        int counter = basket.countByColor(Color.BLUE);
        double totalWeight = basket.calculateTotalWeight();
        System.out.printf("Total weight: %3.2f %n", totalWeight);
        System.out.printf("Total blue balls: %3d", counter);
    }
}
