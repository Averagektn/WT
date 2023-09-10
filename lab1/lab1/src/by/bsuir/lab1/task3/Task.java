package by.bsuir.lab1.task3;

import java.util.ArrayList;

public class Task {
    public static void main(String[] args) {
        printList(solve(1, 2, 0.1), 1, 0.1);
    }
    public static ArrayList<Double> solve(double a, double b, double h){
        ArrayList<Double> values = new ArrayList<>();

        if (a == Double.NEGATIVE_INFINITY || b == Double.POSITIVE_INFINITY || a > b || h <= 0){
            return values;
        }

        for (double x = a; x < b + h; x += h){
            values.add(Math.tan(x));
        }

        return values;
    }

    private static void printList(ArrayList<Double> list, double a, double h){
        System.out.printf("x %4s tg(x)%n", " ");
        for (int i = 0; i < list.size(); i++, a += h){
            System.out.printf("%.2f %6.2f %n", a, list.get(i));
        }
    }
}
