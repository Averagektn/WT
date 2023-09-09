package by.bsuir.lab1.task5;

public class Task {
    public static void main(String[] args){
        solve(new int[]{2, 4, 4, 4, 3, 5, 6});
    }

    private static void solve(int[] numbers){
        System.out.printf("Non-strict search result: %d%n", numbers.length - findIncSubsequenceLength(numbers));
        System.out.printf("Strict search result: %d%n", numbers.length - find(numbers, new int[numbers.length]));
    }

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
        return length + 1;
    }

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