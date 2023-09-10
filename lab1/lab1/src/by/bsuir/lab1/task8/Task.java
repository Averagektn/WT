package by.bsuir.lab1.task8;

public class Task {
    public static void main(String[] args) {
        int[] test = solve(new double[]{0, 1, 2, 3, 4, 6, 7, 9}, new double[]{2, 5, 8, 9, 10});
        printArray(test);
    }

    /**
     * Searches where to put elements of b sequence in a sequence,
     * so that a sequence still be non-decreasing
     * @param a sequence to put elements to
     * @param b sequence to take elements from
     * @return array of indices of elements to be changed
     */
    public static int[] solve(double[] a, double[] b) {
        int[] res = new int[b.length];
        int j = 0;
        for (int i = 0; i < a.length; i++){
            if (a[i] > b[j]) {
                res[j] = i;
                j++;
            }
        }
        for (int k = 0 ; j < b.length; j++, k++){
            res[j] = a.length + k;
        }

        return res;
    }

    /**
     * Prints given array
     * @param array array to print
     */
    private static void printArray(int[] array){
        for (int elem : array){
            System.out.printf("%3d ", elem);
        }
    }
}
