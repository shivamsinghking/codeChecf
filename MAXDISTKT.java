import java.io.*;
import java.util.*;

public class Main
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int t = 1;
      t = sc.nextInt();
      while (t-- > 0)
      {
          solve();
      }
      out.close();
    }

    public static void solve()
    {
       int n = sc.nextInt();
       int[] arr = new int[n];
       int[][] copy = new int[n][2];
       for(int i = 0; i < n; i++){
          arr[i] = sc.nextInt();
          copy[i] = new int[]{arr[i], i};
       }

       Arrays.sort(arr);
       Arrays.sort(copy, (a, b) -> a[0] - b[0]);

       int[] ans = new int[n];
        
       int re = 0;
       for(int i = 0; i < n; i++){
          if(arr[i] == 1){
            ans[i] = 0;
            if(re == 0) re++;
          }else if(arr[i] == 0){
            ans[i] = 0;
            if(re == 0) re++;
          }else{
            if(re > arr[i]-1){
              ans[i] = arr[i]-1;
            }else{
              ans[i] = re;
              re++;
            }
          }
       }

       int[] v = new int[n];
       for(int i = 0 ; i < n; i++){
         v[copy[i][1]] = ans[i] + arr[i];
       }

       parray(v);
    }

    public static void parray(int [] a){
      for(int i = 0 ; i < a.length; i++){
        out.print(a[i] + " ");
      }
      out.println();
      return;
    }

    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    public static int lower_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0, e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) <= k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        return Math.abs(s) - 1;
    }

    public static int upper_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0;
        int e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) < k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        if (s == ar.size())
        {
            return -1;
        }
        return s;
    }

    static class Kioken
    {
        // FileInputStream br = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next()
        {
            while (!st.hasMoreTokens())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt()
        {
            return Integer.parseInt(next());
        }

        public long nextLong()
        {
            return Long.parseLong(next());
        }

        public double nextDouble()
        {
            return Double.parseDouble(next());
        }

        public String nextLine()
        {
            try
            {
                return br.readLine();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext()
        {
            String next = null;
            try
            {
                next = br.readLine();
            }
            catch (Exception e)
            {
            }
            if (next == null || next.length() == 0)
            {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }
    }
}