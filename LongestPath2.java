import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


//this question is same as longest vertical path which contains 1 only but in this slight change that path might not be vertical

public class LongestPath2 {

    private static ArrayList<Integer>[] tree;
    private static int [] values;
    private static int [] dp;
    private static int [] dp2; // for upside V shape

    public static void dfs(int node,int parent) {
        if(values[node]==0) {
            dp[node]=dp2[node]=0;
            return ;
        }

        dp[node]=1;
        ArrayList<Integer> childDPs=new ArrayList<>();

        for(int child:tree[node]) {

            if(child==parent) continue;

            dfs(child,node);
            childDPs.add(dp[child]);
        }

        //compute dp[node]

        if(childDPs.isEmpty()) {
            int maxChildPath= Collections.max(childDPs);
            dp[node]+=maxChildPath;
        }

        //Compute dp2[node] (longest "V" path)

        if(childDPs.size()>=2) {
            //sort children dp values in descending order

            Collections.sort(childDPs,Collections.reverseOrder());
            dp2[node]=1+childDPs.get(0)+ childDPs.get(1);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(); //number of nodes
        tree=new ArrayList[n+1];
        values=new int[n+1];
        dp=new int[n+1];
        dp2=new int[n+1];
        //Initialize th adjacency list

        for(int i=1;i<=n;i++){
            tree[i]=new ArrayList<>();
        }

        //Input the values of the nodes (0 or 1)

        for(int i=1;i<=n;i++){
            values[i]=sc.nextInt();
        }

        //Input the edges
        for(int i=1;i<n;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        //Run the dfs from node 1

        dfs(1,-1);

        int maxPath=0;

        for(int i=1;i<=n;i++){
            maxPath=Math.max(maxPath,dp[i]);
        }
        System.out.println(maxPath);
    }
}
