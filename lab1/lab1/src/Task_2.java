import java.lang.Math;
// NOT DONE YET
public class Task_2 {
    static final double MAX_Y = 5;
    static final double MAX_X_NEGATIVE_Y = 6;
    static final double MAX_X_POSITIVE_Y = 4;
    public static boolean solve(double x, double y){
        boolean result = Math.abs(y) <= MAX_Y;

        if (result){
            if (isPositive(y)){
                result = Math.abs(x) <= MAX_X_POSITIVE_Y;
            } else {
                result = Math.abs(x) <= MAX_X_NEGATIVE_Y;
            }
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
