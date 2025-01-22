import java.util.Scanner;

public class AtCoderVacation {
    public static long findMax(long a,long b,long c){
        return Math.max(a,Math.max(b,c));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        long [] a=new long[n+1];
        long [] b=new long[n+1];
        long [] c=new long[n+1];

        int i=1;
        while(i<=n){
            a[i]=sc.nextLong();
            b[i]=sc.nextLong();
            c[i]=sc.nextLong();
            i++;
        }

        long [] dpa=new long[n+1];
        long [] dpb=new long[n+1];
        long [] dpc=new long[n+1];

        //Base Case:
        dpa[1]=a[1];
        dpb[1]=b[1];
        dpc[1]=c[1];

        //Populating the dp arrays
        i=2;
        while(i<=n){
            dpa[i]=a[i]+Math.max(dpb[i-1],dpc[i-1]);
            dpb[i]=b[i]+Math.max(dpa[i-1],dpc[i-1]);
            dpc[i]=c[i]+Math.max(dpa[i-1],dpb[i-1]);
            i++;
        }
        System.out.println(findMax(dpa[n],dpb[n],dpc[n]));
        sc.close();
    }
}
