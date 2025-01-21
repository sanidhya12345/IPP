import java.util.Scanner;

public class GridGame {
    public long gridGame(int[][] grid) {
        int n=grid[0].length;
        long[][] prefix = new long[2][n];
        for (int i = 0; i < 2; i++) {
            prefix[i][0] = grid[i][0];
            for (int j = 1; j < n; j++) {
                prefix[i][j] = prefix[i][j - 1] + grid[i][j];
            }
        }
        //Iterate through all possible split points
        long minPointsForRobot2 = Long.MAX_VALUE;
        for (int split = 0; split < n; split++) {
            // Calculate the remaining points for robot2
            long pointsRobot2 = Math.max(
                    (split < n - 1 ? prefix[0][n - 1] - prefix[0][split] : 0), // Top row remaining for robot2
                    (split > 0 ? prefix[1][split - 1] : 0) // Bottom row remaining for robot2
            );
            // Robot1 minimizes robot2's maximum points
            minPointsForRobot2 = Math.min(minPointsForRobot2, pointsRobot2);
        }
        return minPointsForRobot2;
    }
    public static void main(String[] args) {
        GridGame obj=new GridGame();
        Scanner sc = new Scanner(System.in);
        // Input the size of the grid
        int n = sc.nextInt();
        int[][] grid = new int[2][n];

        // Fill the grid with input values
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(obj.gridGame(grid));

        sc.close();
    }
}
