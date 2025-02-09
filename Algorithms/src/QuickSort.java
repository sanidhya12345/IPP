import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {


    public static int partition(int [] arr,int low,int high){

        //choose the last element as the pivot
        int pivot=arr[high];

        int i=low-1;

        for(int j=low;j<=high-1;j++){
            if(arr[j]<pivot){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }
    public static void swap(int [] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void quickSort(int [] arr,int low,int high){

        if(low<high){

            int pi=partition(arr,low,high);

            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
