import java.lang.Math;
// THINK OF OUTPUT
public class Task_3 {
    public static void solve(double a, double b, double h){
        for (double x = a; x <= b; x += h){
            System.out.println(Math.tan(x));
        }

    }
}
