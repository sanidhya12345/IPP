import java.util.*;
public class LeftRotateByOne {
    public static void main(String[] args) {
       int [] array={1,2,3,4,5};
       for(int i=0;i<array.length-1;i++){
           int temp=array[i];
           array[i]=array[i+1];
           array[i+1]=temp;
       }
       for(int i=0;i<array.length;i++){
        System.out.println(array[i]);
       }
    }
}