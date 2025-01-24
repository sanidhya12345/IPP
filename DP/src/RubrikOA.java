public class RubrikOA {
        // Helper function to find the maximum of three long values
        public static long maxThree(long a, long b, long c) {
            return Math.max(a, Math.max(b, c));
        }

        // Normal Kadane's algorithm with long values
        public static long normalKadane(int[] arr,int n) {
            long[] k = new long[n];
            k[0] = Math.max(0, arr[0]); // Initialize k[0]

            // Fill the k array using Kadane's logic
            for (int i = 1; i < n; i++) {
                k[i] = maxThree(arr[i], 0, arr[i] + k[i - 1]);
            }

            // Find the maximum value in k
            long maxValue = Long.MIN_VALUE;
            for (long value : k) maxValue = Math.max(value, maxValue);

            return maxValue;
        }

        public static long findMaxSubarraySum(int[] arr,int n, int x) {
            // Arrays to store dp values
            long[] dpMultiplied = new long[n];
            long[] dpNotMultiplied = new long[n];
            long[] k = new long[n];



            // Initialize dp and k values for the first element
            dpMultiplied[0] = Math.max(0, (long) arr[0] * x);
            dpNotMultiplied[0] = Math.max(0, arr[0]);
            k[0] = Math.max(0, arr[0]);

            // Initialize variables to track the maximum result
            long maxSum =maxThree(dpMultiplied[0], dpNotMultiplied[0], k[0]);

            // Iterate through the array to fill dp and k values
            for (int i = 1; i < n; i++) {
                // Update k[i] (no operation ever happened till index i)
                k[i] = Math.max(0, arr[i] + k[i - 1]);

                // Update dpMultiplied[i] (ending at i with arr[i] multiplied by x)
                dpMultiplied[i] = maxThree(0, (long) arr[i] * x,
                        Math.max((long) arr[i] * x + k[i - 1], dpMultiplied[i - 1] + (long) arr[i] * x));

                // Update dpNotMultiplied[i] (ending at i without multiplying arr[i])
                dpNotMultiplied[i] = maxThree(0, arr[i],
                        Math.max(k[i], Math.max(dpMultiplied[i - 1] + arr[i], dpNotMultiplied[i - 1] + arr[i])));

                // Update maxSum
                maxSum = maxThree(maxSum, dpMultiplied[i], dpNotMultiplied[i]);
            }

            return maxSum;
        }

    public static void main(String[] args) {
        int[] arr = {-1, -2, 3, 4, 5, -10};
        int n = arr.length;
        int x=10; //pick some range from array a and multiply with x such that the kadane sum is maximised.
        System.out.println(normalKadane(arr, n));
        System.out.println(findMaxSubarraySum(arr,n,x));
    }
}


