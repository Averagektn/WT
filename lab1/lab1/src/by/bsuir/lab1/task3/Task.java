package by.bsuir.lab1.task3;

public class Task {
    public static void main(String[] args){ solve(1.1, 2.0, 0.05); }
    public static void solve(double a, double b, double h){
        System.out.printf("x %4s tg(x)%n", " ");
        for (double x = a; x < b + h; x += h){
            System.out.printf("%.2f %6.2f %n", x, Math.tan(x));
        }

    }
}
