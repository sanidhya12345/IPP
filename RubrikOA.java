import java.util.*;
//Count the number of arrays whose size is “N” and you are allowed to put any number from “1” to “K” at each index.

/*
Testcase 1:
N=3
K=5
D=[2,-1,-1]
output:50

Testcase 2:
N=3
K=5
D=[2,3,-1]
output:10

* */
public class RubrikOA {

    public static final long MOD=100000007;
    public static boolean hasCycle(int node,int [] visited,List<Integer>[] graph){
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

        //check if the graph contains the cycles or not if yes then the answer is 0
        //because if there is a cycle then any of the vertices have indegree as 2
        // that violates the conditions of the problem

        int [] visitStatus=new int[n];
        for(int i=0;i<n;i++){
            if(visitStatus[i]==0 && hasCycle(i,visitStatus,graph)){
                return 0;
            }
        }

        //process the connected components

        boolean [] visited=new boolean[n];
        long result=1;

        for(int i=0;i<n;i++){
            if(!visited[i]){
                int compSize=dfs(i,visited,graph);
                result=(result*nCr(k,compSize))%MOD;
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
