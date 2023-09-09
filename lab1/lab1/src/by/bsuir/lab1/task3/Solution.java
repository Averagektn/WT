package by.bsuir.lab1.task3;

public class Solution {
    public static void solve(double a, double b, double h){
        for (double x = a; x <= b; x += h){
            System.out.println(Math.tan(x));
        }

    }
}
