import java.util.*;
public class LeftRotateByDplaces {
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int [] array={1,2,3,4,5};
       int d=2;
       //Method 1:
    //    while(d!=0){
    //     for(int i=0;i<array.length-1;i++){
    //         int temp=array[i];
    //         array[i]=array[i+1];
    //         array[i+1]=temp;
    //     }
    //     d--;
    //    }
    int [] rest=new int[d];
    for(int i=0;i<d;i++){
        rest[i]=array[i];
    }
    for(int i=d;i<array.length;i++){
        int temp=array[i];
        array[i]=array[i-d];
        array[i-d]=temp;
    }
    for(int i=array.length-d;i<array.length-1;i++){
        int temp=array[i];
        array[i]=array[i+1];
        array[i+1]=temp;
    }
       for(int i=0;i<array.length;i++){
        System.out.print(array[i]+" ");
       }
       sc.close();
    }
}