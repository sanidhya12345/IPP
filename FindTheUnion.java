import java.util.HashMap;
public class FindTheUnion {
    public static void main(String[] args) {
       int [] arr1={1, 2, 3, 4, 5};
       int [] arr2={1, 2, 3, 6, 7};
       HashMap<Integer,Boolean> visited=new HashMap<>();
       for(int i=0;i<arr1.length;i++){
           if(!visited.containsKey(arr1[i])){
              visited.put(arr1[i],true);
           }
       }
       for(int i=0;i<arr2.length;i++){
         if(!visited.containsKey(arr2[i])){
            visited.put(arr2[i],true);
         }
       }

     System.out.println(visited.keySet());
    }
}