package by.bsuir.lab1.task9_11;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Ball> balls;

    public Basket(List<Ball> balls){
        this.balls = new ArrayList<Ball>(balls);
    }

    public Basket(Ball[] balls){
        this.balls = new ArrayList<Ball>(List.of(balls));
    }

    public void addBall(Ball ball){
        balls.add(ball);
    }

    public void removeBall(int index){
        balls.remove(index);
    }

    public Ball getBall(int index){
        return balls.get(index);
    }

    public int countByColor(Color color){
        int counter = 0;

        for(Ball ball : balls){
            if (ball.getColor() == color){
                counter++;
            }
        }

        return counter;
    }

    public double calculateTotalWeight(){
        double totalWeight = 0;

        for (Ball ball : balls){
            totalWeight += ball.getWeight();
        }

        return totalWeight;
    }
}