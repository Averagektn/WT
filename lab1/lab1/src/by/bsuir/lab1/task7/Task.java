package by.bsuir.lab1.task7;

public class Task {
    public static void main(String[] args) {
        double[] test = new double[]{1, 5, 2, 8, 0, -2};
        sort(test);
        printArray(test);
    }

    /**
     * Sorts the given array
     *
     * @param array array to sort
     */
    public static void sort(double[] array) {
        int i = 0;
        while (i < array.length - 1) {
            if (array[i] > array[i + 1]) {
                swap(array, i, i + 1);
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
            }
        }
    }

    /**
     * Swaps two elements of the array
     *
     * @param array  given array
     * @param index1 index to swap
     * @param index2 index to swap
     */
    private static void swap(double[] array, int index1, int index2) {
        double tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Prints given array
     *
     * @param array array to print
     */
    private static void printArray(double[] array) {
        for (double elem : array) {
            System.out.printf("%.2f ", elem);
        }
    }

}
