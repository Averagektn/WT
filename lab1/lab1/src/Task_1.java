import java.lang.Math;
// NOT DONE YET
// CHANGE CODE
// THERE IS NO DIVISION BY ZERO
// ADD TESTS
// OUTPUT
public class Task_1 {
    public static double solve(double x, double y){
        double numerator = 1 + Math.pow(Math.sin(x + y), 2);
        double denominator = 2 + Math.abs(x - (2 * x) / (1 + Math.pow(x, 2) * Math.pow(y, 2)));
        return numerator / denominator + x;
    }
}
