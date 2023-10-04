package by.bsuir.lab1.task9_11;

import java.awt.Color;

/**
 * Represents a ball with color and weight
 */
public class Ball {
    private Color color;

    private double weight;

    /**
     * Creates ball with given color anf weight
     *
     * @param color  ball color
     * @param weight ball weight
     */
    public Ball(Color color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
