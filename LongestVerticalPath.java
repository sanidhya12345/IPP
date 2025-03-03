import java.util.*;

public class LongestVerticalPath {

    private static ArrayList<Integer> [] tree;
    private static int [] values;
    private static int [] dp;

    public static void dfs(int node,int parent){
        if(values[node]==0){
            dp[node]=0;
            return;
        }

        dp[node]=1;
        int maxChildPath=0;

        //now visit the children of the node

        for(int child:tree[node]){

            if(child==parent) continue; //Avoid revisit of the parent

            dfs(child,node);
            maxChildPath=Math.max(maxChildPath,dp[child]);
        }
        dp[node]+=maxChildPath;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(); //number of nodes
        tree=new ArrayList[n+1];
        values=new int[n+1];
        dp=new int[n+1];

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
