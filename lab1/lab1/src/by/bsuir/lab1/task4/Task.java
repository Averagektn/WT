package by.bsuir.lab1.task4;

import java.util.ArrayList;

public class Task {
    public static void main(String[] args) {
        System.out.println(solve(new int[]{1, 2, 2, 4, 5, 101}));
    }

    /**
     * Finds primes in the given array
     *
     * @param numbers array to find primes
     * @return List of prime indices
     */
    public static ArrayList<Integer> solve(int[] numbers) {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            if (isPrime(numbers[i])) {
                indexes.add(i);
            }
        }

        return indexes;
    }

    /**
     * Checks if n is prime value
     *
     * @param n number to check
     * @return true if n is prime, false otherwise
     */
    private static boolean isPrime(int n) {

        if (n <= 1) {
            return false;
        }

        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 5; i <= Math.sqrt(n) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
