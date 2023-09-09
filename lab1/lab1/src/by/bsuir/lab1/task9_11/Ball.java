package by.bsuir.lab1.task9_11;
import java.awt.Color;

public class Ball {
    private Color color;
    private double weight;

    public Ball(Color color, double weight){
        this.color = color;
        this.weight = weight;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }
}