import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    public static void merge(int [] arr,int left,int mid,int right){
        //find the size of the left and right subarrays
        int size1=mid-left+1;
        int size2=right-mid;

        //create the left and right subarray of the size which is calculated earlier
        int [] leftPart=new int[size1];
        int [] rightPart=new int[size2];

        //copy the left and right subarray elements from the original array

        for(int i=0;i<size1;i++){
            leftPart[i]=arr[i+left];
        }
        for(int j=0;j<size2;j++){
            rightPart[j]=arr[mid+1+j];
        }

        //Merge the temporary arrays

        //intital indices of the left and right subarrays
        int i=0,j=0;

        //initial index of the merged subarrays

        int k=left;

        while(i<size1 && j<size2){
            if(leftPart[i]<=rightPart[j]){
                arr[k]=leftPart[i];
                i++;
            }
            else{
                arr[k]=rightPart[j];
                j++;
            }
            k++;
        }

        //copy the remaining element from the left array if any

        while(i<size1){
            arr[k]=leftPart[i];
            i++;
            k++;
        }

        //copy the remaining element from the right array if any

        while(j<size2){
            arr[k]=rightPart[j];
            j++;
            k++;
        }
    }
    public static void mergeSort(int [] arr,int left,int right){
        if(left<right){

            int mid=(left+right)/2;

            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] arr=new int[n]; // 50 60 10 30 70 40
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
}
