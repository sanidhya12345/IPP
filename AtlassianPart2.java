import java.util.*;
public class AtlassianPart2 {

    private static final Scanner sc=new Scanner(System.in);

    private static void bfs(long n,long src,long [] visited,long [] level,long [] valueArray,ArrayList<ArrayList<Long>> graph){
        Queue<Long> queue=new LinkedList<>();
        if(valueArray[(int)src]==1){
            queue.add(src);
            visited[(int)src]=1;
            level[(int)src]=0;
        }
        while(!queue.isEmpty()){
            long vert=queue.peek();
            queue.poll();
            for(long u:graph.get((int)vert)){
                if(visited[(int)u]==0){
                    if(valueArray[(int)u]==1){
                        visited[(int)u]=1;
                        queue.add(u);
                        level[(int)u]=Math.min(level[(int)u],level[(int)vert]+1);
                    }
                    else{
                        level[(int)u]=Long.MAX_VALUE;
                    }
                }
            }
        }
        if(level[(int)n]>= Long.MAX_VALUE){
            System.out.println("we are unable to react to the final node");
            return ;
        }
        System.out.println("Shortest Path Distance: "+level[(int)n]);
    }
    private static void virusBfs(long n,long k,long src,long [] visited,long [] level,long [] valueArray,ArrayList<ArrayList<Long>> graph){
        Queue<Long> queue=new LinkedList<>();
        queue.add(src);
        visited[(int) src]=1;
        level[(int)src]=0;

        while(!queue.isEmpty()){
            long v=queue.peek();
            queue.poll();
            for(long u:graph.get((int) v)){
                if(visited[(int)u]==0){

                    if(level[(int)u]<=k){
                        valueArray[(int)u]=0;
                        visited[(int)u]=1;
                        level[(int)u]=level[(int)v]+1;
                        queue.add(u);
                    }
                    else{
                        break;
                    }
                }
            }
        }
    }
    private static ArrayList<ArrayList<Long>> buildGraph(long n,long m){
        ArrayList<ArrayList<Long>> graph=new ArrayList<>();
        for(long i=1;i<=n+1;i++){
            graph.add(new ArrayList<>());
        }
        for(long i=1;i<=m;i++){
            long u=sc.nextLong();
            long v=sc.nextLong();
            graph.get((int) u).add(v);
            graph.get((int)v).add(u);
        }
        return graph;
    }
    public static void main(String[] args) {

        long n=sc.nextLong(); //number of vertices
        long m=sc.nextLong(); //number of edges
        long k=sc.nextLong();
        long virusVertex=sc.nextLong();
        long [] valueArray=new long[(int)n+1];
        long [] visited=new long[(int)n+1];
        long [] level=new long[(int)n+1];
        ArrayList<ArrayList<Long>> graph=buildGraph(n,m);
        for(int i=1;i<=n;i++){
            valueArray[i]=sc.nextLong();
            visited[i]=0;
            level[i]=0;
        }
        virusBfs(n,k,virusVertex,visited,level,valueArray,graph);
        long [] visited1=new long[(int)n+1];
        long [] level1=new long[(int)n+1];
        for(int i=1;i<=n;i++){
            visited1[i]=0;
            level1[i]=Long.MAX_VALUE;
        }
        bfs(n,1,visited1,level1,valueArray,graph);
    }
}
