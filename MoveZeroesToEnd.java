import java.util.*;
public class MoveZeroesToEnd {
    public static void main(String[] args) {
       int [] array={2,0,3,4,0,5};
       HashMap<Integer,Integer> map=new HashMap<>();
       int rank=1;
       for(Integer i:array){
            if(i!=0){
               map.put(rank,i);
               rank++;
            }
       }
       int index=1;
       for(int i=0;i<map.size();i++){
         array[i]=map.get(index);
         index++;
       }
       for(int i=map.size();i<array.length;i++){
          array[i]=0;
       }
       System.out.println(Arrays.toString(array));
    }
}