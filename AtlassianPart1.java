import java.util.*;
public class AtlassianPart1 {
    private static final Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        long n=sc.nextLong(); //number of vertices
        long m=sc.nextLong(); //number of edges

        long [] valueArray=new long[(int)n+1];
        long [] level=new long[(int)n+1];
        long [] visited=new long[(int) n+1];
        for(long i=1;i<=n;i++){
            valueArray[(int)i]=sc.nextLong();
            level[(int)i]=Long.MAX_VALUE;
            visited[(int)i]=0;
        }
        //creating the graph
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

        Queue<Long> queue=new LinkedList<>();
        //if the source node value is 0 then there will be the blocking point.
        if(valueArray[1]==1){
            queue.add(1L);
            visited[1]=1;
            level[1]=0;
        }
        while(!queue.isEmpty()){
            long v=queue.peek();
            queue.poll();
            //System.out.print(v+" ");
            for(long u:graph.get((int) v)){
                if(visited[(int)u]==0){
                    if(valueArray[(int)u]==1){
                        visited[(int)u]=1;
                        queue.add(u);
                        level[(int)u]=Math.min(level[(int)u],level[(int)v]+1);
                    }
                    else{
                        level[(int)u]=Long.MAX_VALUE;
                    }
                }
            }
        }
        //level[n] will be the answer if it is not contains the inf value
        if(level[(int)n]>=Long.MAX_VALUE) System.out.println("we cannot reach to the final node");
        else System.out.println(level[(int)n]);
        sc.close();
    }
}
