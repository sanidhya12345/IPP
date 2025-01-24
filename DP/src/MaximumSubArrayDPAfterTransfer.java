import java.util.Scanner;

public class MaximumSubArrayDPAfterTransfer {

    // Helper function to find the maximum of three values
    public static long maxThree(long a, long b, long c, long l) {
        return Math.max(a, Math.max(b, c));
    }

    public static long maximumSubarrayDP(int[] a, int[] b) {
        int n = a.length;

        // Arrays to store dp values
        long[] dpTransfer = new long[n];
        long[] dpNonTransfer = new long[n];
        long[] k = new long[n];

        // Initialize variables to track the maximum result
        long maxSum = Long.MIN_VALUE;

        // Initialize dp and k values for the first element
        dpTransfer[0] = Math.max(0, b[0]); // If we transfer the first element
        dpNonTransfer[0] = Math.max(0, a[0]); // If we do not transfer the first element
        k[0] = Math.max(0, a[0]); // Best sum subarray ending at index 0 without any transfer

        // Update maxSum for the first element
        maxSum = Math.max(dpTransfer[0], dpNonTransfer[0]);

        // Iterate through the array to fill dp and k values
        for (int i = 1; i < n; i++) {
            // Update k[i] (best sum subarray ending at i without any transfer)
            k[i] = Math.max(0, a[i] + k[i - 1]);

            // Update dpTransfer[i] (best sum subarray ending at i with transfer at some point)
            dpTransfer[i] = maxThree(0, b[i], b[i] + k[i - 1], b[i] + dpTransfer[i - 1]);

            // Update dpNonTransfer[i] (best sum subarray ending at i without any transfer)
            dpNonTransfer[i] = Math.max(0, a[i] + dpNonTransfer[i - 1]);

            // Update maxSum
            maxSum = Math.max(maxSum, Math.max(dpTransfer[i], dpNonTransfer[i]));
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example arrays
        int[] a = {-100, -200, 10, -100, -1000};
        int[] b = {-100, -200, -1000, 100, -1000};

        long result = maximumSubarrayDP(a, b);
        System.out.println("Maximum Subarray Sum After Transfer: " + result);
    }
}
