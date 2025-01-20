import java.util.HashMap;
import java.util.Scanner;

class Pair{
    int first;
    int second;
    public Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
}
public class FirstCompletelyPaintedRowOrColumn {
    // this check funtion complexity is o(n^2) that's why it is inefficient to use.
//    public static boolean check(boolean [][] vis,int m,int n){
//        boolean flag1=false;
//        boolean flag2=false;
//        for(int i=0;i<m;i++){
//            int countRow=0;
//            for(int j=0;j<n;j++){
//                if(vis[i][j])
//                    countRow++;
//            }
//            if(countRow==m){
//                flag1=true;
//                break;
//            }
//        }
//        for(int i=0;i<m;i++){
//            int countCol=0;
//            for(int j=0;j<n;j++){
//                if(vis[j][i])
//                    countCol++;
//            }
//            if(countCol==n){
//                flag2=true;
//                break;
//            }
//        }
//        return flag1 || flag2;
//    }
    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Map each value to its position in the matrix
        HashMap<Integer, Pair> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new Pair(i, j));
            }
        }

        // Track the number of marked elements in each row and column
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // Process elements in the order of `arr`
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            Pair pos = map.get(ele);
            int row = pos.first;
            int col = pos.second;

            // Mark the element and update counters
            rowCount[row]++;
            colCount[col]++;

            // Check if any row or column is complete
            if (rowCount[row] == n || colCount[col] == m) {
                return i;
            }
        }

        return -1; // No complete row or column found
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int [] arr=new int[m*n];
        for(int i=0;i<m*n;i++)arr[i]=sc.nextInt();
        int [][] mat=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=sc.nextInt();
            }
        }
        System.out.println(firstCompleteIndex(arr,mat));
    }
}