import java.util.*;

public class Q1 {

    public static int solve(int [] arr,int m){
        int n=arr.length;
        int sum=0;
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        for(int j=0;j<n;j++){
            sum+=arr[j];
            int remainder=sum%m;
            if(remainder<0) remainder+=m;
            count+=map.getOrDefault(remainder,0);
            map.put(remainder,map.getOrDefault(remainder,0)+1);
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int [] arr=new int[n];
        System.out.println(solve(arr,m));
    }
}
