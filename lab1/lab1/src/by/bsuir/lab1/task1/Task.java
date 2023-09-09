package by.bsuir.lab1.task1;
public class Task {
    public static void main(String[] args){ System.out.println(solve(10, 20)); }
    private static double solve(double x, double y) {
        double numerator = 1 + Math.pow(Math.sin(x + y), 2);
        double denominator = 2 + Math.abs(x - (2 * x) / (1 + Math.pow(x, 2) * Math.pow(y, 2)));
        return numerator / denominator + x;
    }
}
