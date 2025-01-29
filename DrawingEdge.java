import java.util.*;
public class DrawingEdge {
    public static long power(long x, long y, long p)
    {
        long res = 1;
        while (y > 0) {
            if ((y & 1) != 0)
                res = res * x;
            y = y >> 1;
            x = x * x;
        }
        return res % p;
    }
    public static long solve(long n,long MOD){
        long possibilities=n*(n-1)/2;
        return power(2,possibilities,MOD)%MOD;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong();
        long MOD=1000000007;
        System.out.println(solve(n,MOD));
        sc.close();
    }
}
