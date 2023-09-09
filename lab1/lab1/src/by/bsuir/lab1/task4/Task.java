package by.bsuir.lab1.task4;

public class Task {
    public static void main(String[] args){
        solve(new int[]{1, 2, 2, 4, 5, 101});
    }

    private static void solve(int[] numbers){
        for(int i = 0; i < numbers.length; i++) {
            if (isPrime(numbers[i])) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPrime(int n){
        if (n % 2 == 0) {
            return false;
        }
        if (n <= 1) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(n) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
