package by.bsuir.lab1.task6;

public class Task {
    public static void main(String[] args){
        printArray(solve(new int[]{ 1, 2, 3, 4, 5 }));
    }

    public static int[][] solve(int[] numbers){
        int length = numbers.length;
        int[][] result = new int[length][length];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                result[j][i] = numbers[(j + i) % length];
            }
        }

        return result;
    }

    private static void printArray(int[][] array){
        for (int[] rows : array) {
            for (int elem : rows) {
                System.out.printf("%3d", elem);
            }
            System.out.println();
        }
    }
}