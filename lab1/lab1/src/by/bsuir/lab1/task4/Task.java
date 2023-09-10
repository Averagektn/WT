package by.bsuir.lab1.task4;

import java.util.ArrayList;

public class Task {
    public static void main(String[] args){
        System.out.println(solve(new int[]{ 1, 2, 2, 4, 5, 101 }));
    }

    public static ArrayList<Integer> solve(int[] numbers){
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < numbers.length; i++) {
            if (isPrime(numbers[i])) {
                indexes.add(i);
            }
        }

        return indexes;
    }

    private static boolean isPrime(int n){

        if (n <= 1) {
            return false;
        }

        if (n <= 3){
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
