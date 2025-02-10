import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static int find(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int[] leafNodes(int preorder[], int n) {
        int[] inorder = preorder.clone();
        int[] visited = new int[n];
        Arrays.fill(visited, 0);
        Arrays.sort(inorder);
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int node = preorder[i];
            int index = find(inorder, node);
            visited[index] = 1;
            int left = index - 1;
            int right = index + 1;

            // Correcting index out-of-bounds issue
            boolean isLeftVisited = (left >= 0) ? visited[left] == 1 : true;
            boolean isRightVisited = (right < n) ? visited[right] == 1 : true;

            if (isLeftVisited && isRightVisited) {
                list.add(preorder[i]);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
