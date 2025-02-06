import java.util.Scanner;

public class StarsAndBars {
    private static final Scanner sc=new Scanner(System.in);
    private static final long MOD=100000007;

    public static long fact(long n){
        long fact=1;
        for(long i=1;i<=n;i++){
            fact=fact*i;
        }
        return fact;
    }
    public static long power(long base,long exp,long MOD){
        long result=1;
        base=base%MOD;
        while(exp>0){
            if((exp & 1)==1){
                result=(result*base)%MOD;
            }
            base=(base*base)%MOD;
            exp>>=1;
        }
        return result;
    }
    public static long modInverse(long a,long mod){
        return power(a,mod-2,mod);
    }
    public static long nCr(long n,long r){
        if(r>n) return 0;
        long result=1;
        for(int i=0;i<r;i++){
            result=(result*(n-i))%MOD;
            result=(result*modInverse(i+1,MOD))%MOD;
        }
        return result;
    }
    public static void solveOne(){
        long n=sc.nextLong();
        long r=sc.nextLong();
        //given n distinct things no we have to select r things from them
        //formula is nCr
        long ans=nCr(n,r);
        System.out.println("nCr: "+ans);

        //What if we want to know the number of ways to select R items from N distinct items;
        // but internal ordering is also considered

        long ans1=nCr(n,r)*fact(r);
        System.out.println("nCr*r! "+ans1);

        long k=sc.nextLong();
        //Given k boxes; find the number of ways to put n balls in them. All n balls are same;
        //formula is; -> (N+K-1) {C} (K-1)

        long ans2=nCr(n+k-1,k-1);
        System.out.println("(N+K-1) {C} (K-1): "+ans2);

        long m=sc.nextLong();
        //Given two linear sequences of size N and M;
        // find the number of ways to generate a sequence of N+M size;
        // such that order of both the sequences remain the same

        long ans3=nCr(m+n,m);
        System.out.println(" n + m {C} m: "+ans3);
    }
    public static void main(String[] args) {
        solveOne();
        sc.close();
    }
}
