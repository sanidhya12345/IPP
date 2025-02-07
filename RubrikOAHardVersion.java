import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//the change in this hard version we will deal with the condition of non decreasing array
//if there is a cycle then the answer for that component is k only
//slight change in the code previously we simply return 0 because that time we deal only with distinct values
//instead of returning 0 we will multiply with k in the result
public class RubrikOAHardVersion {
    public static final long MOD=100000007;
    public static boolean hasCycle(int node, int [] visited, List<Integer>[] graph){
        //marks the starting vertex as visited.
        visited[node]=1;
        for(int neighbour:graph[node]){
            if(visited[neighbour]==1) return true; //cycle detected
            if(visited[neighbour]==0 && hasCycle(neighbour,visited,graph)) return true;
        }
        visited[node]=2; //visited
        return false;
    }
    public static long power(long base,long exp,long mod){
        long result=1;
        base=base%mod;
        while(exp>0){
            if((exp & 1)==1){
                result=(result * base) %mod;
            }
            base=(base * base )%mod;
            exp>>=1;
        }
        return result;
    }
    public static long modInverse(long a,long mod){
        return power(a,mod-2,mod);
    }
    public static long nCr(long n,long r){
        if(r>n) return 0;
        long result=1;
        for(int i=0;i<r;i++){
            result=(result *(n-i))%MOD;
            result=(result *modInverse(i+1,MOD))%MOD;
        }
        return result;
    }
    public static int dfs(int node,boolean [] visited,List<Integer> [] graph){
        visited[node]=true;
        int size=1;
        for(int neighbour:graph[node]){
            if(!visited[neighbour]){
                size+=dfs(neighbour,visited,graph);
            }
        }
        return size;
    }
    public static long solveOne(int n,int k,int [] d ){

        List<Integer> [] graph=new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }

        //build the graph based on the conditions

        for(int i=0;i<n;i++){
            if(d[i]!=-1){
                graph[i].add(d[i]-1);
            }
        }


        //process the connected components

        boolean[] visited = new boolean[n];
        long result = 1;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Check if the current component has a cycle
                int[] visitStatus = new int[n];
                boolean cycleExists = hasCycle(i, visitStatus, graph);

                if (cycleExists) {
                    result = (result * k) % MOD; // If cycle exists, answer for this component is K
                } else {
                    int compSize = dfs(i, visited, graph);
                    result = (result * nCr(k + compSize - 1, compSize-1)) % MOD;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int [] d=new int[n];
        for(int i=0;i<n;i++){
            d[i]=sc.nextInt();
        }
        System.out.println(solveOne(n,k,d));
    }
}
