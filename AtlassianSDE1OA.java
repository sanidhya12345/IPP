import java.util.*;

public class AtlassianSDE1OA {

    //Easy Version: Every prod is having the same cost
    public static long solveOne(int n,long [] pods,long [] cost){

        Arrays.sort(pods);
        long finalcost = 0;

        for (int i = 1; i < n; i++) {
            if (pods[i] <= pods[i - 1]) { // If duplicate or less
                long diff = pods[i - 1] - pods[i] + 1; // Increase needed
                pods[i] += diff;
                finalcost += diff; // Each increment costs 1
            }
        }
        return finalcost;
    }
    //Hard Version: Every prod is having the different cost.

    public static Map<Integer, Integer> frequencyMap(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    public static int minCostToMakeUnique(int[] ar, int[] c) {
        int n = ar.length;
        Map<Integer, Integer> freqMap = frequencyMap(ar);
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Add elements with their cost to the heap
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new int[]{c[i], ar[i]});
        }

        int totalCost = 0;
        Set<Integer> seen = new HashSet<>();

        while (!maxHeap.isEmpty()) {
            int[] element = maxHeap.poll();
            int cost = element[0];
            int num = element[1];

            if (!seen.contains(num)) {
                seen.add(num);
            } else {
                // Find the next unique number to change to
                int newNum = num;
                while (seen.contains(newNum)) {
                    newNum++;
                }
                seen.add(newNum);
                totalCost += cost;
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] pods=new int[n];
        for(int i=0;i<n;i++) pods[i]=sc.nextInt();
        int [] cost=new int[n];
        for(int i=0;i<n;i++) cost[i]=sc.nextInt();
        //System.out.println(solveOne(n,pods,cost));
        System.out.println(minCostToMakeUnique(pods,cost));
    }
}
