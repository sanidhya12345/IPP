import java.util.Scanner;

public class Max2ConsAllowed {
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

        // Base Case for i=1;

        dpa[1]=a[1];
        dpb[1]=b[1];
        dpc[1]=c[1];

        if (n > 1) {
            dpa[1] = a[1] +findMax(a[0],b[0],c[0]);
            dpb[1] = b[1] + findMax(a[0],b[0],c[0]);
            dpc[1] = c[1] + findMax(a[0],b[0],c[0]);
        }
        i=2;
        while(i<=n){
            dpa[i] = Math.max(a[i] + Math.max(dpb[i - 1], dpc[i - 1]), a[i] + a[i - 1] + Math.max(dpb[i - 2], dpc[i - 2]));
            dpb[i] = Math.max(b[i] + Math.max(dpa[i - 1], dpc[i - 1]), b[i] + b[i - 1] + Math.max(dpa[i - 2], dpc[i - 2]));
            dpc[i] = Math.max(c[i] + Math.max(dpa[i - 1], dpb[i - 1]), c[i] + c[i - 1] + Math.max(dpa[i - 2], dpb[i - 2]));
            i++;
        }
        System.out.println(findMax(dpa[n],dpb[n],dpc[n]));
        sc.close();
    }
}
