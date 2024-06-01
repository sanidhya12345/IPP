import java.util.*;
public class LinearSearch {
    public static boolean linearSearch(int element,int [] arr,int size){
        for(int i=0;i<size;i++){
            if(arr[i]==element){
               return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of array:- ");
        int size=sc.nextInt();
        int [] arr=new int[size];
        for(int i=0;i<size;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println("Enter the element you want to search:-");
        int element=sc.nextInt();
        System.out.println(linearSearch(element,arr,size));
    }
}