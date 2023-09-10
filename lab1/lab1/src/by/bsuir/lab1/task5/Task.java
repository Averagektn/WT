package by.bsuir.lab1.task5;

public class Task {
    public static void main(String[] args){
        solve(new int[]{2, 4, 4, 4, 3, 5, 6});
    }

    /**
     * Counts the least number of elements to be deleted to get non-decreasing sequence
     * @param numbers initial sequence
     * @return Number of elements to be deleted
     */
    public static int solve(int[] numbers){
        return solve(numbers, false);
    }

    /**
     * Counts the least number of elements to be deleted to get increasing or non-decreasing sequence
     * @param numbers initial sequence
     * @param isStrict increasing(true) or non-decreasing(false) sequence
     * @return Number of elements to be deleted
     */
    public static int solve(int[] numbers, boolean isStrict){
        if (isStrict){
            return numbers.length - findIncSubsequenceLength(numbers);
        } else {
            return find(numbers, new int[numbers.length]);
        }
    }

    /**
     * Finds non-decreasing subsequence in given sequence
     * @param sequence sequence to search
     * @return Length of non-decreasing subsequence
     */
    private static int findIncSubsequenceLength(int[] sequence){
        if (sequence.length == 1) {
            return 1;
        }

        int[] subsequenceLengths = new int[sequence.length];

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[i] >= sequence[j]) {
                    if (subsequenceLengths[i] <= subsequenceLengths[j]) {
                        subsequenceLengths[i] = subsequenceLengths[j] + 1;
                    }
                }
            }
        }

        int max = 0;

        for (int length : subsequenceLengths) {
            max = Math.max(max, length);
        }

        return max + 1;
    }

    /**
     * Finds the longest increasing subsequence
     * @param arr given sequence
     * @param subsequence subsequence of indices
     * @return Length of the subsequence
     */
    private static int find(int[] arr, int[] subsequence) {

        if (arr.length <= 1) {
            return 1;
        }

        int length = -1;

        int[] size = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            subsequence[i] = -1;
            size[i] = -1;
        }

        subsequence[0] = arr[0];
        size[0] = 0;

        for (int i = 1; i < arr.length; i++) {
            size[i] = ceilIndex(subsequence, i, arr[i]);

            if (length < size[i]) {
                length = size[i];
            }
        }
        return length;
    }

    /**
     * Finds ceil index
     * @param subsequence given subsequence
     * @param startRight right border
     * @param key key to compare
     * @return Ceil index
     */
    private static int ceilIndex(int[] subsequence, int startRight, int key){
        int mid, left = 0, right = startRight, index = 0;
        boolean isIndex = false;

        for (mid = (left + right) / 2; left <= right && !isIndex; mid = (left + right) / 2) {
            if (subsequence[mid] < key) {
                right = mid - 1;
            }
            else if (mid + 1 <= right && subsequence[mid + 1] < key) {
                subsequence[mid + 1] = key;
                index = mid + 1;
                isIndex = true;
            } else {
                left = mid + 1;
            }
        }

        if (!isIndex) {
            if (mid == left) {
                subsequence[mid] = key;
                index = mid;
            }
            else {
                subsequence[mid + 1] = key;
                index = mid + 1;
            }
        }

        return index;
    }
}