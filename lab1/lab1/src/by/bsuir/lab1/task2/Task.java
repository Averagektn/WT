package by.bsuir.lab1.task2;

public class Task {
    /**
     * Maximum possible y coordinate
     */
    private static final double MAX_Y = 5;

    /**
     * Minimum possible y coordinate
     */
    private static final double MIN_Y = -3;

    /**
     * Maximum possible x coordinate with negative y
     */
    private static final double MAX_X_NEGATIVE_Y = 6;

    /**
     * Maximum possible x coordinate with positive y
     */
    private static final double MAX_X_POSITIVE_Y = 4;

    public static void main(String[] args) {
        System.out.println(solve(2.5, 3.5));
    }

    /**
     * Calculates if the {x, y} point is in the marked area
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return true if {x, y} is in the area, false in other cases
     */
    public static boolean solve(double x, double y) {
        boolean result;

        if (isPositive(y)) {
            result = y <= MAX_Y && Math.abs(x) <= MAX_X_POSITIVE_Y;
        } else {
            result = y >= MIN_Y && Math.abs(x) <= MAX_X_NEGATIVE_Y;
        }

        return result;
    }

    /**
     * Checks if value is positive
     *
     * @param y value
     * @return result of comparison with 0
     */
    private static boolean isPositive(double y) {
        return y >= 0;
    }

    /**
     * Checks if value is positive
     *
     * @param y value
     * @return result of comparison with 0
     */
    private static boolean isPositive(int y) {
        return y >= 0;
    }

}
