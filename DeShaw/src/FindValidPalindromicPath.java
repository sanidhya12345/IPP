import java.util.*;
public class FindValidPalindromicPath {

    private static ArrayList<Integer>[] tree;
    private static char [] values;
    private static HashMap<Character,Integer> map=new HashMap<>();

    public static void dfs(int node,int parent){
        char c=values[node];

        //update the character frequency

        map.put(c,map.getOrDefault(c,0)+1);

        //check if the current path make the palindrome or not

        int oddcount=0;

        for(int val:map.values()){

            if(val%2!=0) oddcount++;
        }

        // If at most 1 character has an odd count, it's a valid palindrome permutation
        System.out.println("Node " + node + ": " + (oddcount > 1 ? "NO" : "YES"));

        for(int child:tree[node]){
            if(child!=parent){  //avoid revisiting the children
                dfs(child,node);
            }
        }
        map.put(c,map.get(c)-1);
        if(map.get(c)==0) map.remove(c);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        tree=new ArrayList[n+1];
        values=new char[n+1];
        for(int i=1;i<=n;i++){
            values[i]=sc.next().charAt(0);
        }
        //Initialize th adjacency list

        for(int i=1;i<=n;i++){
            tree[i]=new ArrayList<>();
        }
        //Input the edges
        for(int i=1;i<n;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        dfs(1,-1);
        sc.close();
    }
}
