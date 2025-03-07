import java.util.*;
public class Q2 {

    private static ArrayList<Integer>[] tree;
    private static int [] values;
    private static int count;
    private static HashMap<Integer,Integer> map=new HashMap<>();

    public static void dfs(int node,int parent,int sum, boolean [] visited,int mod){
        sum+=values[node];
        int remainder=sum%mod;
        if(remainder<0) remainder+=mod;

        // count the paths where sum%mod==0

        count+=map.getOrDefault(remainder,0);

        map.put(remainder,map.getOrDefault(remainder,0)+1);

        visited[node]=true;

        //DFS to visit all children

        for(int child:tree[node]){
            if(!visited[child]){
                dfs(child,node,sum,visited,mod);
            }
        }

        //Backtrack; remove the current node's contribution

        map.put(remainder,map.getOrDefault(remainder,0)-1);
    }
    public static int countVerticalValidPaths(int n,int mod){
        boolean [] visited=new boolean[n+1];
        count=0;
        //base case: when the node itself is divisible by mod
        map.put(0,1);

        //start the dfs from the root node.
        dfs(1,-1,0,visited,mod);

        return count;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();  // the number of nodes
        int mod=sc.nextInt();
        tree=new ArrayList[n+1];
        values=new int[n+1];
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
        System.out.println(countVerticalValidPaths(n,mod));
        sc.close();
    }
}
