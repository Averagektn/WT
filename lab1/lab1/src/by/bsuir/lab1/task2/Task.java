package by.bsuir.lab1.task2;

public class Task {
    private static final double MAX_Y = 5;
    private static final double MIN_Y = -3;
    private static final double MAX_X_NEGATIVE_Y = 6;
    private static final double MAX_X_POSITIVE_Y = 4;

    public static void main(String[] args) {
        System.out.println(solve(2.5, 3.5));
    }
    private static boolean solve(double x, double y){
        boolean result;

        if (isPositive(y)) {
            result = y <= MAX_Y && Math.abs(x) <= MAX_X_POSITIVE_Y;
        } else {
            result = y >= MIN_Y && Math.abs(x) <= MAX_X_NEGATIVE_Y;
        }

        return result;
    }

    private static boolean isPositive(double y){
        return y >= 0;
    }

    private static boolean isPositive(int y){
        return y >= 0;
    }
}
