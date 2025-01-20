import java.util.Arrays;

public class PaintHouse4 {
    public static long minCost(int n,int [][] cost){
        //3d-dp array to store the minimum cost of painting houses
        //n/2-as mentioned in the problem the equidistant houses also not have the same color.
        long [][][] dp=new long[n/2][3][3];

        for(int row=0;row<n/2;row++){
            for(int i=0;i<3;i++){
                Arrays.fill(dp[row][i],Long.MAX_VALUE);
            }
        }

        //Initialize the dp arrya for the first and the last house i.e. 0th house and the (n-1)th house.

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i!=j){
                    dp[0][i][j]=cost[0][i]+cost[n-1][j];
                }
            }
        }

        //Dp calculation for the rest of the houses.
        for(int row=1;row<n/2;row++){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i==j) continue; // skip when the  adjacent houses are of same color.

                    long mPrev=Long.MAX_VALUE;

                    // Search for previous valid (i1, j1) where i1 != i, j1 != j, and i1 != j1
                    for (int i1 = 0; i1 < 3; i1++) {
                        for (int j1 = 0; j1 < 3; j1++) {
                            if (i1 != i && j1 != j && i1 != j1) {
                                mPrev = Math.min(mPrev, dp[row - 1][i1][j1]);
                            }
                        }
                    }
                    dp[row][i][j] = mPrev + cost[row][i] + cost[n - row - 1][j];
                }
            }
        }
        // Final answer is the minimum cost from the last row dp[n/2 - 1] considering i != j
        long result = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    result = Math.min(result, dp[n / 2 - 1][i][j]);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int n=4;
        int [][] cost={{3,5,7},{6,2,9},{4,8,1},{7,3,5}};
        System.out.println(minCost(n,cost));
    }
}
