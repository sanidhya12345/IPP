import java.io.*;
import java.util.StringTokenizer;
public class AdvitiiyaSecurtiyVault {
    private static final StdIn in=new StdIn();
    private static final PrintWriter out=new PrintWriter(System.out);
    private static final long MOD=1000000007;
    public static long power(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
    public static void solveOne(){
        int n=in.nextInt();
        long zero=0;
        long k=in.nextLong();
        long [] a=new long[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextLong();
            if(a[i]==0) zero++; //count for the unknown digits.
        }
        long ans=power(k,zero); //find the possible subsequences(including non-palindromic ones)
        long cnt = 0; // Count positions that contribute to palindromes
        for (int i = 0; i < (n + 1) / 2; i++) {
            if (a[i] == 0 && a[n - 1 - i] == 0) { // Both positions are unknown
                if (i == n - 1 - i) // If it's the center of an odd-length string
                    cnt++;
                else
                    cnt += 2;
            } else if (a[i] != 0 && a[n - 1 - i] != 0) { // Both positions are known
                if (a[i] != a[n - 1 - i]) { // If they are different, no palindrome is possible
                    cnt = 0;
                    break;
                }
            }
        }

        long twice = power(k, cnt); // Compute sequences that include both a string and its reverse

        long palindromes = power(k, (cnt + 1) / 2); // Compute only palindromic sequences
        twice -= palindromes; // Remove redundant palindromic cases from twice

        long two_inverse = (MOD - MOD / 2) % MOD; // Compute modular inverse of 2 (for division)
        twice = (twice * two_inverse) % MOD; // Apply modular inverse to get distinct non-palindromic sequences
        out.println((ans - twice + MOD) % MOD);
    }
    public static void main(String[] args) {
        //number of testcases;
        int testcases=in.nextInt();
        while(testcases--!=0){
            solveOne();
        }
        out.flush();
        out.close();
    }
}
class StdIn
{
    BufferedReader br;
    StringTokenizer st;

    public StdIn()
    {
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException  e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    long nextLong()
    {
        return Long.parseLong(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}